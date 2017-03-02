<?php
    session_start();
    require('functions_rec.php');
    require_once('../../DB/functions.php');
    
    $u = $_SESSION['UID'];
    unset($_SESSION['PREDICTION_QUEST']);
    unset($_SESSION['PREDICTION_CLASSIC']);
    unset($_SESSION['PREDICTION_AMS']);
    
    if(checkQuestIsEmpty($u)){  //To control if user has made the questionnaire or not
        $_SESSION['quest'] = "genre";
        header("Location: ../questionnaire/survayBIG5.php");
    }else{
        
        $recommendation = findRecommendationsByGenres($u);
        
        $_SESSION['PREDICTION_GENRES'] = $recommendation;
        header("Location: ../../index.php");
    }
?>


