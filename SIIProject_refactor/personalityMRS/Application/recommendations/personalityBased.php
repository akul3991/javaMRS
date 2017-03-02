<?php
    session_start();
    require('functions_rec.php');
    require_once('../../DB/functions.php');

    $u = $_SESSION['UID'];
    unset($_SESSION['PREDICTION_CLASSIC']);
    unset($_SESSION['PREDICTION_GENRES']);
    unset($_SESSION['PREDICTION_AMS']);
    
    if(checkQuestIsEmpty($u)){  //To control if user has made the questionnaire or not
        $_SESSION['quest'] = "base";
        header("Location: ../questionnaire/survayBIG5.php");
    }else{
       // if ((NOTexistsRecommendation($u, FALSE))){
        $recommendation = findRecommendations($u,5, FALSE);
        //}else{
            //$recommendedSong = retrieveRecommendedSong($u,FALSE);
            //$recommendation = findNextRecommendations($recommendedSong, FALSE, FALSE);
        $_SESSION['PREDICTION_QUEST'] = $recommendation;
        header("Location: ../../index.php");
    }
?>



