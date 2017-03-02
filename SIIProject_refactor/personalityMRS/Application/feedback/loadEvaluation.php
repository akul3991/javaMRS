<?php
    session_start();
    require_once("../../DB/functions.php");
    require("systemEvaluation.php");
    
    $submitOK = 0;
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $check=1;
        
        if (empty($_POST["REC"])) {
            $rec = "";
            $submitOK = 1;
        } else {
            $rec = $_POST["REC"];
        }
        

        if($submitOK == 0){
         $feedback = getFeedbacks($rec);
         $nomefile ="feedback.txt";
         $fp = fopen($nomefile,"w");
         fputs ($fp,$feedback);
        fclose($fp);

        
        if (file_exists($nomefile)) {
            header('Content-Description: File Transfer');
            header('Content-Type: application/octet-stream');
            header('Content-Disposition: attachment; filename='.basename($nomefile));
            header('Expires: 0');
            header('Cache-Control: must-revalidate');
            header('Pragma: public');
            header('Content-Length: ' . filesize($nomefile));
            readfile($nomefile);
            exit;
        }
        
        $nomefile2 ="Evaluations.txt";
        $fp1 = fopen($nomefile2,"w");
        $evaluations = getRecEvaluations($rec);
        fputs ($fp1,$evaluations);
        fclose($fp1);

        }
        
    } else {
        $check = 0;
        $rec = "";
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


    
    <h2>Recupera valutazione</h2>
    <h3>Seleziona il sistema di raccomadnazione</h3>
                
    <br><br><br><br>
    <br>

    <form id="form1" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
                    <center><select id="select" name = "REC" style="width:100px">
                            <option value="QUEST">QUEST</option>
                            <option value="GENRES">GENRES</option>
                            <option value="AMS">AMS</option>
                            <option value="CLASSIC">CLASSIC</option>
                    </select></center>
                <br>
            <br>
    <center><input type="submit" name="submit" value="INVIO"></center>
    </form>
    </body>
</html>