<?php
    session_start();
    require_once("../../DB/functions.php");
    require("computeResult.php");
    
    $answerList=$_SESSION['AnswerBIG5'];
    for($i=0; $i<=sizeof($answerList)-1; $i++) {
        $q=$i+1;
        ${'q'.$q} = $answerList[$i];
    }
   
    $age=$_SESSION['AGE'];
    $gender=$_SESSION['GENDER'];
    $uid = $_SESSION['UID'];
    $nationality = $_SESSION['NATION'];
    $work = $_SESSION['PROFESSION'];
    $education = $_SESSION['EDUCATION'];
 
    addAnswersBIG5test($uid, $gender, $age, $nationality, $work, $education, $q1, $q2, $q3, $q4, $q5,$q6,$q7,$q8,$q9,$q10,$q11,$q12,$q13,$q14,$q15,$q16,$q17,$q18,$q19,$q20,$q21,$q22,$q23,$q24,$q24,$q25,$q26,$q27,$q28,$q29,$q30,$q31,$q32,$q33, $q34,$q35,$q36,$q37,$q38,$q39,$q40,$q41,$q42,$q43,$q44);
    
    unset($_SESSION['age']);
    unset($_SESSION['NATION']);
    unset($_SESSION['PROFESSION']);
    unset($_SESSION['EDUCATION']);
    
    $reversedAnswers = reverseScored($answerList);
    
    $ext = BFIExtraversionScore($reversedAnswers);
    $agr  =  BFIAgreeablenessScore($reversedAnswers);
    $con = BFIConscientiousScore($reversedAnswers);
    $neu =  BFINeuroticismScore($reversedAnswers);
    $ope = BFIOpennessScore($reversedAnswers);
    
    
    addPersonalityBIG5($uid, $ope, $neu, $ext, $con, $agr);
    unset($_SESSION['AnswerBIG5']);

    if ($_SESSION['quest'] == "base")
        header("Location: ../recommendations/personalityBased.php");
    else
        header("Location: ../recommendations/personality_genres.php");

?>