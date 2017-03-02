<?php
    session_start();
    require('../predictPersonality/predictPersonality.php');
    require('functions_rec.php');
    require_once('../../DB/functions.php');

    $u = $_SESSION['UID'];
    unset($_SESSION['PREDICTION_QUEST']);
    unset($_SESSION['PREDICTION_CLASSIC']);
    unset($_SESSION['PREDICTION_GENRES']);

    if (!($_SESSION['PREDICTION_FB'] == " Sorry! No prediction could be made")){
        //if (NOTexistsRecommendation($u, TRUE)){
            $recommendation = findRecommendations($_SESSION['FBID'],5, TRUE);
        //}else{
        // $recommendedSong = retrieveRecommendedSong($u,TRUE);
        //  $recommendation = findNextRecommendations($recommendedSong, TRUE, FALSE);
        //}
        $_SESSION['PREDICTION_AMS'] = $recommendation;
    }else{
        $_SESSION['PREDICTION_AMS'] = $_SESSION['PREDICTION_FB'];
    }
    header("Location: ../../index.php");
?>
