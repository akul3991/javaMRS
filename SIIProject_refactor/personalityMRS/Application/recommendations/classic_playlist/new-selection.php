<?php
    session_start();
    require_once('../../lastFMFunction/suggestion.php');
    require_once('../../../DB/functions.php');
   
    $tracksToShow = array();
    $tracks = retrieveLibrarySong($_SESSION['UID']);

    $titles = array();
    $artists = array();
 
    $limit=0;
    $n=2*(ceil(10/sizeof($tracks)));
    foreach($tracks as $track){
        array_push($titles, stripslashes(strtolower($track[1])));
    }
    $contSong=0;
    foreach($tracks as $track){
        if( $contSong<10){
            $tracksToShowTemp = array();
            $songsSimilars = getSimilars($track[0], $track[1], $n);
            for($i=0; $i<sizeof($songsSimilars); $i++){
                $title = strtolower($songsSimilars[$i][1]);
                if(!(in_array($title, $titles)) && !(in_array($songsSimilars[$i][0], $artists))){
                    array_push($artists, $songsSimilars[$i][0]);
                    array_push($tracksToShowTemp, $songsSimilars[$i]);
                }
            }
            if (sizeof($tracksToShowTemp)>$limit){
                $limit=sizeof($tracksToShowTemp);
            }
            array_push($tracksToShow, $tracksToShowTemp);
            $contSong++;
        }else{
            break;
        }
    }
    $tracksToShowOK = array();
    $cont = 0;
    $k = 0;
    while($k!=10 && $cont!=$limit){
        foreach($tracksToShow as $plusSong){
            if($k<10){
                if($cont<sizeof($plusSong)){
                    array_push($tracksToShowOK, $plusSong[$cont]);
                    $k++;
                }
            } else {
                break;
            }
        }
        $cont++;
    }
    $_SESSION['similarTrack'] = $tracksToShowOK;
    header("Location: initialForm.php");
?>