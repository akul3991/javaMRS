<?php
session_start();
    require_once('../../DB/functions.php');
    require('functions_rec.php');

    $u = $_SESSION['UID'];
    unset($_SESSION['PREDICTION_QUEST']);
    unset($_SESSION['PREDICTION_GENRES']);
    unset($_SESSION['PREDICTION_AMS']);
    
    if(checkLibraryIsEmpty($u)){  //<---- //To control if user library is empty
        header("Location: classic_playlist/initialForm.php");
        return;
    }else{
        $recommendedSongs = retrieveLibrarySong($u);
        $recommendation = findNextRecommendations($recommendedSongs, FALSE, TRUE);
        $_SESSION['PREDICTION_CLASSIC'] = $recommendation;
        header("Location: ../../index.php");
        return;
    }
?>