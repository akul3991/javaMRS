<?php
    session_start();
    require_once("../../DB/functions.php");
    
    $questions = array("Ho trovato nuove canzoni di artisti a me già noti", "Ho trovato canzoni di artisti che non conoscevo e che da ora vorrei inziare ad ascoltare", "Queste canzoni sono lontane da quelle che ascolto normalmente", "Ho trovato interessante la playlist suggerita", "Userei nuovamente questo suggeritore di playlist in futuro");
    $resultFinal=array();
    $submitOK = 0;
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $check=1;
        
        if (empty($_POST["box"])) {
            $box = "";
            $_SESSION['FEEDBACK'] = $box;
        } else {
            $box = test_input($_POST["box"]);
            $_SESSION['FEEDBACK'] = $box;
        }
        for ($i=1; $i<=5; $i++) {
            if (empty($_POST["answer".$i])) {
                ${'answer'.$i} = "";
                $submitOK = 1;
            } else {
                ${'answer'.$i} = $_POST["answer".$i];
            }
        }
        if($submitOK == 0){
            for($i=1; $i<=5; $i++) {
                array_push($resultFinal, ${'answer'.$i});
            }
            $_SESSION['AnswerFinal']=$resultFinal;
            
            if (isset($_SESSION['PREDICTION_AMS'])){
                $_SESSION['SURVAY']="AMS";
                unset($_SESSION['PREDICTION_AMS']);
            }elseif(isset($_SESSION['PREDICTION_QUEST'])){
                $_SESSION['SURVAY']="QUEST";
                unset($_SESSION['PREDICTION_QUEST']);
            }elseif(isset($_SESSION['PREDICTION_CLASSIC'])){
                $_SESSION['SURVAY']="CLASSIC";
                unset($_SESSION['PREDICTION_CLASSIC']);
            }elseif(isset($_SESSION['PREDICTION_GENRES'])){
                $_SESSION['SURVAY']="GENRES";
                unset($_SESSION['PREDICTION_GENRES']);
            }
            header("Location: ../feedback/saveEvaluation.php");
            return;
        }
        
    } else {
        $check = 0;
        $box = "";
        for($i=1; $i<=5; $i++) {
            ${'answer'.$i} = "";
        }
    }
        
    function test_input($data) {
        $data = trim($data);
        $data = stripslashes($data);
        //$data = htmlspecialchars($data);
        return $data;
    }
?>

<!DOCTYPE HTML>

<html>
    <head>
        <link href="http://www.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <link type="text/css" href="https://netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="../../CSS/styles.css" rel="stylesheet" />
    </head>
    <body id="gallery">


    
    <h2>Valutazione finale</h2>
    <h3>Esprimi un giudizio sulla playlist raccomandata</h3>
                
    <br><br><br><br>
    <br>

    <form id="form1" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
                <div id="tableContainer-1">
                <div id="tableContainer-2">
                <table id="myTable">
                <?php
                for($i=1; $i<=5; $i++) {?>
                <TR>
                   <TD class="quest1"><b><?php echo $questions[$i-1]?></b></TD>
                   <TD> <select id="select" name=<?php echo "answer".$i ?> >
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </select>
                </TR>
              <?php  }?>
                </table>
                <br>
                <center><p>NOTA: 1: molto in disaccordo - 5: molto d'accordo</p></center>
       <br>
    <center><textarea name="box" placeholder="Lascia un feedback"></textarea></center>
<br>
    <center><input type="submit" name="submit" value="INVIO"></center>
    </form>
    </body>
</html>