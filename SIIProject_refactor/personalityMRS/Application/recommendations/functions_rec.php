<?php
require_once('../../DB/functions.php');
require_once('../lastFMFunction/suggestion.php');
require('playlist_by_genre/functions_by_genre.php');

    
    //Find user u neighborhoods
    function findNeighborhood($values_u,$neighborhood, $FB){
        $idTOpersonality =  findUsersLFM();
        $neighborhoods = array();
        $similarities = array();
        
        foreach($idTOpersonality as $v=>$values_v){
            
            $simp = simp($values_u,$values_v,$FB);
            $similarities[$v] = $simp;
           
        }
       
        
        arsort($similarities, SORT_NUMERIC);
       
        $n=0;
        foreach($similarities as $key=>$value){
            if($n==$neighborhood){
                break;
            }
            array_push($neighborhoods, $key);
            $n++;
            
        }
        return $neighborhoods;
    }
    
    
    //CosineSimilarity
    function simp($values_u,$values_v,$FB){
        $pu = $values_u;
        $pv = $values_v;
        $num = 0;
        $den = 0;
        $den1 = 0;
        $den2 = 0;
        for ($k=0;$k<5;$k++){
            $num = $num +($pu[$k]*$pv[$k]);
            $den1 = $den1 + ($pu[$k]*$pu[$k]);
            $den2 = $den2 + ($pv[$k]*$pv[$k]);
        }
        $den = sqrt($den1)*sqrt($den2);
        
        return $num/$den;
    }
    
    //Find first recommendation based on personality
    function findRecommendations($u,$neighborhoods, $FB){
        $u_values= findPersonality($u,$FB);
        $recommendation = array();
        $recommendation2 = array();
        
        $neighborhood = findNeighborhood($u_values,$neighborhoods, $FB);
        
        foreach($neighborhood as $neighborID){
            $tracks = selectTracks($neighborID, 4);
            $cont = 0;
            foreach($tracks as $trackID=>$value){
                $artist = key($value);
                $title = $value[key($value)];
                $artistID = md5($artist);
                if($cont<2){
                    if (!(array_key_exists($artist, $recommendation))){
                        addTracksAndArtist($artistID, $artist, $trackID, $title);
                        addRecommendedSongs($trackID, $_SESSION['UID'], $FB);
                        $recommendation[$artist]=$value[$artist] ;
                    }
                    $cont++;
                } elseif (!(array_key_exists($artist, $recommendation)) && !(array_key_exists($artist, $recommendation2))){
                        addTracksAndArtist($artistID, $artist, $trackID, $title);
                        addRecommendedSongs($trackID, $_SESSION['UID'], $FB);
                        $recommendation2[$artist]=$value[$artist] ;
                }
            }
        }
        return array_merge($recommendation,$recommendation2);
    }
    
    //Find next recommendations based on similarity

    function findNextRecommendations($recSongs, $FB, $CLASSIC){
        $recommendation = array();
        $cont=0;
        $n=2*(ceil(10/sizeof($recSongs)));
        foreach($recSongs as $track){
            if($cont<15){
                if($CLASSIC)
                    $similarsSongs = getSimilarsNoImage($track[0], $track[1], $n);
                else
                    $similarsSongs = getSimilarsNoImage($track[0], $track[1], 2);

                foreach(array_keys($similarsSongs) as $similarArtist){
                    $similarArtist = $similarArtist;
                    $title = $similarsSongs[$similarArtist];
                    if ((trackISNOTrecommended($recSongs, $similarArtist, $title)) && !(array_key_exists($similarArtist, $recommendation))) {
                        //$title = preg_replace("/\(.*\)/", "", $title);
                        $recommendation[$similarArtist]=$title;
                        $cont++;
                    }
                }
            }else{
                break;
            }
        }
        $output = array_slice($recommendation, 0, 15);
        foreach(array_keys($output) as $value){
            $trackID = md5($output[$value].$value);
            $artistID = md5($value);
            addTracksAndArtist($artistID, $value, $trackID, $output[$value]);
            if(!$CLASSIC)
                addRecommendedSongs($trackID, $_SESSION['UID'], $FB);
            else
                addLibrary($_SESSION['UID'], $trackID);
        }
        return $output;
    }
    
    
    
    function trackISNOTrecommended($recommendedSongs, $artist, $title){
        foreach($recommendedSongs as $track){
            $artistRec = $track[0];
            $titleRec =  $track[1];
            if(($artistRec == $artist) && ($titleRec == $title)){
                return FALSE;
            }
        }
        return TRUE;
    }
    
    
   function findRecommendationsByGenres($u){
       
       $recommendation = array();
       $songsFound = array();
       $genres = getGenres($u);
       $cont=1;
       $n= sizeof($genres);
       if($n-15<0){
           $cont++;
           $tmp = ($n-15)*(-1);
           while($tmp-$n>=0){
               $tmp = $tmp -$n;
               $cont++;
           }
       }
       foreach($genres as $genre){
           $control = 0;
           $songsFound = getSongByGenres($genre);
               foreach($songsFound as $song){
                   if($cont!=$control){
                       $artist=$song[0];
                       $title=$song[1];
                       if(!(array_key_exists($artist, $recommendation))){
                           $recommendation[$artist]=$title;
                           $control++;
                       }
                   }else{
                       break;
                   }
               }
        }
       
       foreach(array_keys($recommendation) as $value){
           $trackID = md5($recommendation[$value].$value);
           $artistID = md5($value);
           addTracksAndArtist($artistID, $value, $trackID, $recommendation[$value]);
           addRecommendedSongsByGenres($trackID, $u);
       }
    return $recommendation;
    }
?>