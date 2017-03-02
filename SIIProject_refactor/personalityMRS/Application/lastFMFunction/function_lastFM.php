<?php

    $api_key = "9c5daa5cd358dac7398d78764413518c";
    
    function getToptracks(){
        $url = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=50";
        return curl($url);
    }


    function getTrackSimilar($artist, $track){
        $url = "http://ws.audioscrobbler.com/2.0/?method=track.getsimilar&artist=$artist&track=$track&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=20";
        return curl($url);
    }
    
    
    function getArtistToptrack($artist){
        $url = "http://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=$artist&api_key=9c5daa5cd358dac7398d78764413518c&format=json";
        return curl($url);
    }


    function searchtrack($track){
        $url = "http://ws.audioscrobbler.com/2.0/?method=track.search&track=$track&api_key=9c5daa5cd358dac7398d78764413518c&format=json";
        return curl($url);
    }


     function topTrackBygenres($genre){
        $url = "http://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=$genre&api_key=9c5daa5cd358dac7398d78764413518c&format=json&limit=50";
        return curl($url);
    }


    function curl($url){
        $url= preg_replace('/ /', '+', $url);
        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        $result = curl_exec($ch);
        curl_close($ch);
        return $result;
    }
?>