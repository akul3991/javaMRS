<?php
    require('function_lastFM.php');
    
    //return an array (($title, $artist, $image), (($title2, $artist2, $image2)), (), ..);
    function getSuggestions(){
        
        $suggestions = array();
        $tracks = getToptracks();
        $artists = array();
        $json_file = json_decode($tracks, true);
        $topTracks = $json_file['tracks']['track'];   
        
        $contSong=0;
        foreach($topTracks as $track){
            if($contSong<10){
                $title = $track['name'];
                $artist = $track['artist']['name'];
                if(!(in_array($artist, $artists))){
                    array_push($artists, $artist);
                    if (isset($track['image'])){
                        foreach($track['image'] as $im){
                            if($im['size'] == "extralarge")
                                $image = $im['#text'];
                        }
                        array_push($suggestions, array($artist, $title, $image));
                        $contSong++;
                    }
                }
            }
        }
        return $suggestions;
    }
    
    //return similar songs for artist and song given
    function getSimilars($artist, $song, $n){
        $artists = array();
        $suggestions = array();
        $similars = getTrackSimilar($artist, $song);
        $json_file = json_decode($similars, true);
        
        if(isset($json_file['similartracks']) && isset($json_file['similartracks']['track'])){
            $similartracks = $json_file['similartracks']['track'];
            $contSong=0;
            if(sizeof($similartracks)!=1){
                foreach($similartracks as $track){
                    if($contSong<$n){
                        $title = $track['name'];
                        $artistN = $track['artist']['name'];
                        if(!(in_array($artistN, $artists))){
                            array_push($artists, $artistN);
                            if (isset($track['image'])){
                                foreach($track['image'] as $im){
                                    if($im['size'] == "extralarge")
                                        $image = $im['#text'];
                                }
                                array_push($suggestions, array($artistN, $title, $image));
                                $contSong++;
                            }else{
                                array_push($suggestions, array($artistN, $title, null));
                                $contSong++;
                            }
                        }
                    }
                }
            }
       }
        return $suggestions;
    }
    
    function getSimilarsNoImage($artist, $song, $n){
        $suggestions = array();
        $similars = getTrackSimilar($artist, $song);
        $json_file = json_decode($similars, true);
        if(isset($json_file['similartracks']) && isset($json_file['similartracks']['track'])){
            $similartracks = $json_file['similartracks']['track'];
            $contSong=0;
            if(sizeof($similartracks)!=1){
                foreach($similartracks as $track){
                    if($contSong<=$n && sizeof($suggestions)!=$n){
                        if(isset($track['name']) && isset($track['artist']['name'])) {
                            $title = $track['name'];
                            $artistN = $track['artist']['name'];
                            $suggestions[$artistN]=$title;
                            $contSong++;
                        }
                    }else{
                        break;
                    }
                }
            }
        }
        return $suggestions;
    }
    
    function getArtistToptracks($artist){
        
        $suggestions = array();
        $tracks = getArtistToptrack($artist);
        $json_file = json_decode($tracks, true);
        $topTracks = $json_file['toptracks']['track'];
        $contSong=0;
        foreach($topTracks as $track){
            if($contSong<10){
                $title = $track['name'];
                if (isset($track['image'])){
                    foreach($track['image'] as $im){
                        if($im['size'] == "large")
                        $image = $im['#text'];
                    }   
                    array_push($suggestions, array($artist, $title, $image));
                    $contSong++;
                }else{
                    array_push($suggestions, array($artist, $title, null));
                    $contSong++;
                }
            }
        }
        return $suggestions;
    }
    
    function search_Track($track){
        $tracks = searchtrack($track);
        $suggestions = array();
        $json_file = json_decode($tracks, true);
        if(isset($json_file['results']) && isset($json_file['results']['trackmatches'])){
            $tracksFound = $json_file['results']['trackmatches']['track'];
            if($json_file['results']['opensearch:totalResults']!=1){
                foreach($tracksFound as $trackfound){
                    $title =  $trackfound['name'];
                    $artist =  $trackfound['artist'];
                    if (isset( $trackfound['image'])){
                        foreach( $trackfound['image'] as $im){
                            if($im['size'] == "large")
                                $image = $im['#text'];
                        }
                        array_push($suggestions, array($artist, $title, $image));
                    }else{
                        array_push($suggestions, array($artist, $title, null));
                    }
                }
            }else{
                $title =  $tracksFound['name'];
                $artist =  $tracksFound['artist'];
                if (isset( $tracksFound['image'])){
                    foreach( $tracksFound['image'] as $im){
                        if($im['size'] == "large")
                            $image = $im['#text'];
                    }
                    array_push($suggestions, array($artist, $title, $image));
                }else{
                    array_push($suggestions, array($artist, $title, null));
                }
            }
        }
        return $suggestions;
    }
    
    //find top track by genre
    function getSongByGenres($genre){
        
        $artists = array();
        $suggestions = array();
        $tracks = topTrackBygenres($genre);
        $json_file = json_decode($tracks, true);
        $topTracks = $json_file['toptracks']['track'];
        $contSong=0;
        foreach($topTracks as $track){
            if($contSong<15){
                $title = $track['name'];
                $artist = $track['artist']['name'];
                if(!(in_array($artist, $artists))){
                    array_push($artists, $artist);
                    array_push($suggestions, array($artist, $title));
                    $contSong++;
                }
            }
        }
        return $suggestions;
    }
?>