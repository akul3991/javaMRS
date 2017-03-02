<?php
session_start();
?>
<!doctype html>
<html xmlns:fb="http://www.facebook.com/2008/fbml">
  <head>
    <meta charset="utf-8">
    <title>Raccomandazione Musicale basata sulla Personalità</title>
    <link href="http://www.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link type="text/css" href="https://netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <script src="Application/echonest-spotify/spotify_en_tools.js"></script>
    <script src="Application/echonest-spotify/recVisualization.js"></script>
    <link type="text/css" href="CSS/styles.css" rel="stylesheet" />
  </head>
  <body id="index">
    <?php require_once("DB/functions.php");
      if (isset($_SESSION['FBID'])):
        $_SESSION['UID'] = getUID($_SESSION['FBID']) ;
      
    ?>      <!--  After user login  -->
    <div class="container2">
        <h2>Ciao <?php echo $_SESSION['FULLNAME']; ?>! </h2>
        <h3><p>&Egrave la tua personalità a decidere  qual è la playlist giusta per te!</p></h3>
        <h3><p>Ascoltale <b>TUTTE</b> e 4 e ricordati di <b>VALUTARE</b> ogni playlist che ascolti!</p></h3>
    </div>
    <div class="container3">
        <img src="https://graph.facebook.com/<?php echo $_SESSION['FBID']; ?>/picture?height=120&width=120">
    </div>
    <div>
        <b><p id="instruction"><a href="./instruction.html">SEGUI LE ISTRUZIONI!!</a><p></b>
        <p id="privacy"><a href="./privacy.html" target="_blank">Privacy</a><p>
        <p id="logout"><a href="./Login/logout.php">Esci</a><p>
        <?php 
            if ($_SESSION['FBID'] == 10206014389786057){
        ?>
                <p id="evaluation"><a href="./Application/feedback/loadEvaluation.php" target="_blank">Valutazione</a><p> 
        <?php  } ?>
    </div>


    <?php if (isset($_SESSION['PREDICTION_AMS'])): {?>     <!--  After user personality predictions based on likes   -->


        <?php if ($_SESSION['PREDICTION_AMS'] == " Sorry! No prediction could be made") {
                unset($_SESSION['PREDICTION_AMS']);?>
        <br><br><br>
        <center><p>Ci dispiace, ma non hai abbastanza like</p>
        <div id="playlistButt"><input type="button" id="border_radius" value="Torna all'homepage" onclick="top.location.href = 'index.php'" /></div></center>
            
        <?php } else {
       
                   $recommendation = $_SESSION['PREDICTION_AMS']; ?>
                   
                    <script type="text/javascript">
                        var jArray= new Array();
                    </script>
                    
                    <?php  foreach(array_keys($recommendation) as $artist){?>
                            <script type="text/javascript">
                                var artist= "<?php echo $artist; ?>";
                                var track= "<?php echo $recommendation[$artist]; ?>";
                                jArray.push([artist, track]);
                            </script>

                <?php
                    }?>

                    <div class="playlistBox">
                        <div id="info"> </div>
                            <div id="all_results"> </div>
                                <ul class="nav nav-list">
                    <script type="text/javascript">
                        findPreview(jArray);
                    </script>



       <?php
           
           if (notHasSurvayFinal($_SESSION['UID'], "AMS")){?>
                <div class="center">
                    <div id="playlistButt"><input type="button" id="border_radius" value="Valuta questa playlist" onclick="top.location.href = './Application/questionnaire/survayFinal.php'" /></div>    <!-- Next personality prediction, fb -->
               </div>
    <?php }else{?>
                <div class="center">
                    <!--<div id="playlistButt"><input type="button" id="border_radius" value="Clicca per aver un'altra playlist" onclick="top.location.href = './Application/recommendations/personalityAMS.php'" /></div>     Next personality prediction, fb -->

                    <?php   unset($_SESSION['PREDICTION_AMS']);?>
                    <div id="playlistButt"><input type="button" id="border_radius" value="Torna all'homepage" onclick="top.location.href = 'index.php'" /></div>    <!-- Next personality prediction, fb -->
                </div>
            </ul></div>
        <?php
     }
}
        }elseif (isset($_SESSION['PREDICTION_QUEST'])): {?>    <!--  After user personality predictions  based on questionnaire -->

            <?php $recommendation = $_SESSION['PREDICTION_QUEST'];?>
                <script type="text/javascript">
                    var jArray2= new Array();
                </script>
            <?php  foreach(array_keys($recommendation) as $artist){?>

                    <script type="text/javascript">
                        var artist= "<?php echo $artist; ?>";
                        var track= "<?php echo $recommendation[$artist]; ?>";
                        jArray2.push([artist, track]);
                    </script>
            <?php }?>
            <div class="playlistBox">
                <div id="info"> </div>
                    <div id="all_results"> </div>
                        <ul class="nav nav-list">

                            <script type="text/javascript">
                                findPreview(jArray2);
                            </script>

            <?php if (notHasSurvayFinal($_SESSION['UID'], "QUEST")){?>
                <div class="center">
                    <div id="playlistButt"><input type="button" id="border_radius" value="Valuta questa playlist" onclick="top.location.href = './Application/questionnaire/survayFinal.php'" /></div>    <!-- Next personality prediction, fb -->
                </div>
            <?php }else{?>
                <div class="center">
                    <!-- <div id="playlistButt"><input type="button" id="border_radius" value="Clicca per aver un'altra playlist" onclick="top.location.href = './Application/recommendations/personalityBased.php'" /></div>    Next personality prediction, fb -->
                    <?php   unset($_SESSION['PREDICTION_QUEST']);?>
                    <div id="playlistButt"><input type="button" id="border_radius" value="Torna all'homepage" onclick="top.location.href = 'index.php'" /></div>    <!-- Next personality prediction, fb -->
                </div>
                </ul></div>
    <?php    }
            
        }elseif (isset($_SESSION['PREDICTION_CLASSIC'])): {?>    <!--  After user personality predictions  based on questionnaire -->
<?php $recommendation = $_SESSION['PREDICTION_CLASSIC'];?>
<script type="text/javascript">
var jArray2= new Array();
</script>
<?php  foreach(array_keys($recommendation) as $artist){?>

<script type="text/javascript">
var artist= "<?php echo $artist; ?>";
var track= "<?php echo $recommendation[$artist]; ?>";
jArray2.push([artist, track]);
</script>


<?php  
    }?>

<div class="playlistBox">
    <div id="info"> </div>
        <div id="all_results"> </div>
            <ul class="nav nav-list">

                <script type="text/javascript">
                    findPreview(jArray2);
                </script>

<?php if (notHasSurvayFinal($_SESSION['UID'], "CLASSIC")){?>
    <div class="center">
        <div id="playlistButt"><input type="button" id="border_radius" value="Valuta questa playlist" onclick="top.location.href = './Application/questionnaire/survayFinal.php'" /></div>    <!-- Next personality prediction, fb -->
        <div id="playlistButt"><input type="button" id="border_radius" value="Torna alla selezione delle canzoni" onclick="top.location.href = './Application/recommendations/classic_playlist/initialForm.php'" /></div> 
    </div>
<?php }else{?>
        <div class="center">
           <!--  <div id="playlistButt"><input type="button" id="border_radius" value="Clicca per aver un'altra playlist" onclick="top.location.href = './Application/recommendations/classic.php'" /></div>    Next personality prediction, fb -->
            <div id="playlistButt"><input type="button" id="border_radius" value="Torna alla selezione delle canzoni" onclick="top.location.href = './Application/recommendations/classic_playlist/initialForm.php'" /></div> 
        <?php   unset($_SESSION['PREDICTION_CLASSIC']);?>
            <div id="playlistButt"><input type="button" id="border_radius" value="Torna all'homepage" onclick="top.location.href = 'index.php'" /></div>    <!-- Next personality prediction, fb -->
        </div>
        </ul></div>
<?php }
 

    }elseif (isset($_SESSION['PREDICTION_GENRES'])): {?>    <!--  After user personality predictions  based on questionnaire but considering styles -->

<?php $recommendation = $_SESSION['PREDICTION_GENRES'];?>
<script type="text/javascript">
var jArray2= new Array();
</script>
<?php  foreach(array_keys($recommendation) as $artist){?>

<script type="text/javascript">
var artist= "<?php echo $artist; ?>";
var track= "<?php echo $recommendation[$artist]; ?>";
jArray2.push([artist, track]);
</script>


<?php 
    }?>

<div class="playlistBox">
    <div id="info"> </div>
    <div id="all_results"> </div>
    <ul class="nav nav-list">

        <script type="text/javascript">
            findPreview(jArray2);
        </script>

<?php if (notHasSurvayFinal($_SESSION['UID'], "GENRES")){?>
        <div class="center">
            <div id="playlistButt"><input type="button" id="border_radius" value="Valuta questa playlist" onclick="top.location.href = './Application/questionnaire/survayFinal.php'" /></div>    <!-- Next personality prediction, fb -->
        </div>
<?php }else{?>
        <div class="center">
            <!-- <div id="playlistButt"><li><input type="button" id="border_radius" value="Clicca per aver un'altra playlist" onclick="top.location.href = './Application/recommendations/personality_genres.php'" /></div>    Next personality prediction, fb -->
            <?php   unset($_SESSION['PREDICTION_GENRES']);?>
            <div id="playlistButt"><li><input type="button" id="border_radius" value="Torna all'homepage" onclick="top.location.href = 'index.php'" /></div>    <!-- Next personality prediction, fb -->
        </div>
        </ul></div>
     <?php }

        }else: {?>
    <div id="buttonsPlaylist">

        <div id="playlistButt"><input type="button" id="border_radius" value="Playlist basata su generi musicali ricavati dalla personalità" onclick="top.location.href = './Application/recommendations/personality_genres.php'" /></div>        <!--  Before personality prediction, personality & styles-->
        <br>
        <div id="playlistButt"><input type="button" id="border_radius" value="Playlist basata sulla personalità ricavata dai like" onclick="top.location.href = './Application/recommendations/personalityAMS.php'" /> </div> <!-- Before personality prediction, fb -->
        <br>

      <div id="playlistButt"><input type="button" id="border_radius" value="Playlist basata sulla personalità" onclick="top.location.href = './Application/recommendations/personalityBased.php'" /> </div><!--  Before personality prediction, no fb -->
        <br>
      <div id="playlistButt"><input type="button" id="border_radius" value="Playlist basata su preferenze" onclick="top.location.href = './Application/recommendations/classic.php'" /></div>
                            <!--  Before personality prediction, no personality -->
    </div>
    <?php } endif ?>

   <?php else: ?>     <!-- Before login -->
        <div class="container">
        <h1>Sistema di Raccomandazione Musicale basato sulla Personalità</h1>
                    <br><br>
                   Non sei Connesso
        <div>

        <a href="Login/fbconfig.php">Accedi con Facebook</a>
        <br><br><br><br>
        <p> Per qualsiasi dubbio o domanda potete contattarmi alla mail</p>
        <p> melissa.onori@personalitymrs.it</p></div>
</form>
      </div>
    <?php endif ?>
  </body>
</html>