<?php
    session_start();
    require_once("../../DB/functions.php");
    
    $typeRec = $_SESSION['SURVAY'];
    $uid=$_SESSION['UID'];
    $result = $_SESSION['AnswerFinal'];
    $feedback = $_SESSION['FEEDBACK'];
    
    addEvaluation($uid, $typeRec, $result[0], $result[1], $result[2], $result[3],$result[4], $feedback);
    
    unset($_SESSION['SURVAY']);
    unset($_SESSION['AnswerFinal']);
    unset($_SESSION['FEEDBACK']);
    
    header("Location: ../../index.php");
?>