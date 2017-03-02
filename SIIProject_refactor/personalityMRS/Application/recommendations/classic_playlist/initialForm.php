<?php 
    session_start();
    require_once('../../lastFMFunction/suggestion.php');
    require_once('../../../DB/functions.php');
    
    if (isset($_GET["var1"])){
        $_SESSION['trackFound'] = NULL;
        $uid = $_SESSION['UID'];
        $artists = $_GET["var1"];
        $arr = explode(";",$artists);
        for($i=0; $i<sizeof($arr)-1; $i++){
            $artist= explode("_", $arr[$i])[0];
            $song = explode("_", $arr[$i])[1];
            $artistId = md5($artist);
            $trackId = md5($song.$artist);
            addTracksAndArtist($artistId, $artist, $trackId, $song);
            addLibrary($uid, $trackId);
        }
        if($_GET["var2"]==1){
            header("Location: ../classic.php");
        }else{
            header("Location: new-selection.php");
        }
    }
    $submitOK = 0;
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        if (empty($_POST["track"])){
            $submitOK = 1;
            $track = "";
        }else{
            $track = test_input($_POST["track"]);
        }
        if($submitOK == 0){
            $_SESSION['similarTrack'] = NULL;
            $tracksFound = search_Track($track);
            $_SESSION['trackFound']=$tracksFound;
        }
        
    }else{
        $track = "";
    }
     
    function test_input($data) {
        $data = trim($data);
        $data = stripslashes($data);
        return $data;
    }
?>
    <!DOCTYPE HTML>
    <html>
        <head>
            <link href="http://www.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
            <link type="text/css" href="https://netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
            <link type="text/css" href="../../../CSS/styles.css" rel="stylesheet" />
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        </head>
        <body id="gallery">
        <form id="form2" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
         <center><h6><b>Seleziona</b> le tue canzoni preferite e premi il pulsante "<i>Continua la selezione</i>" per sceglierne altre oppure premi il pulsante "<i>Vai alla playlist</i>" per visualizzare la playlist raccomandata! Se non trovi la tua canzone preferita tra quelle suggerite <b>inserisci</b> il titolo nel riquadro e premi "<i>GO</i>"!<br><br> Si consiglia di selezionare o inserire almeno 3 canzoni!</h6></center>
            <br>
            <center><input id="songIns" type="text" name="track" placeholder="inserire titolo canzone..." value="<?php echo $track;?>">
            <input id="findTrack" type="submit" name="submit" value="GO"></center>
            <br><br>
<?php

            if(!isset($_SESSION['similarTrack']) && !isset($_SESSION['trackFound'])){
                $suggestions = getSuggestions();
            }elseif (isset($_SESSION['similarTrack'])){
                $suggestions = $_SESSION['similarTrack'];
            }else{
                $suggestions = array_slice($_SESSION['trackFound'],0,10);
            }
?>
           <div class="center">
<?php
            if(sizeof($suggestions)!=0){
                foreach($suggestions as $track){
                    $image = $track[2];
                    if($image!=null){
?>
                        <div class="img">
                            <img id="<?php echo $track[0]."_".$track[1]?>" src="<?php echo $image?>" width="170" height="170">
                            <div class="descArt"><?php echo $track[0]?></div>
                            <div class="descTrack"><?php echo $track[1]?></div>
                        </div>
<?php               }else{ 
?>
                        <div class="img">
                            <img id="<?php echo $track[0]."_".$track[1]?>" src="../../../images/NoImage.jpg" width="170" height="170">
                            <div class="descArt"><?php echo $track[0]?></div>
                            <div class="descTrack"><?php echo $track[1]?></div>
                        </div>
<?php               }
                } 
?>                
              </div>
          <div class="center">
            <div class="classic" ><input id="border_radius"  type="button" name="select"  value="Continua la selezione" onClick="new_selection('activeImg',0)" /></div>
            <div class="classic" ><input id="border_radius"  type="button" name="playlist" value="Vai alla playlist" onClick="go_to_playlist('activeImg',1)"/> </div>
          </div>
 <?php      }else{
?>
                <h6>Non ci sono canzoni simili a quelle che hai selezionato, per favore inserisci un'altra canzone o ricarica le top tracks</h6>
                <?php unset($_SESSION['similarTrack']);
                    unset($_SESSION['trackFound']);
                ?>
                <div class="classic" ><input id="border_radius"  type="button" name="topTrack" value="Ricarica le top tracks" onclick="window.location.reload()"/> </div>
                
<?php       }
?>
            </form>
            <script>
                $(document).ready(function(){
                                  $("img").click(function(){
                                                 if($(this).hasClass("activeImg"))
                                                    $(this).removeClass("activeImg");
                                                 else
                                                    $(this).addClass("activeImg");
                                });
                                                 });
            function new_selection(myclass, playlist){
                getElementActives(myclass, playlist);
                
            }
            function go_to_playlist(myclass, playlist){
                getElementActives(myclass, playlist);
            }
            function getElementActives(myclass, playlist){
                var artists = [];
                var tracks = [];
                var ids = "";
                var x = document.getElementsByClassName(myclass);
                    for(var i=0; i<x.length; i++){
                        var id = x[i].id;
                        ids = ids.concat(id).concat(";");
                    }
                //console.log(ids);
                window.location.assign("initialForm.php?var1="+ids+"&var2="+playlist);
            }
            </script>
        </body>
    </html>