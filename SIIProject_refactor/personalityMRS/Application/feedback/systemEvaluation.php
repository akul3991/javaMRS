<?php
    
    
    function getFeedbacks($type_rec){
        
        $evaluations = retrieveEvaluation($type_rec); 
        $feedback = $evaluations[5];
        
        return $feedback;
    }
    
    function getRecEvaluations($type_rec){
        
        $evaluations = retrieveEvaluation($type_rec); 

        $novelty = $evaluations[0];
        $serendipity = $evaluations[1];
        $diversity = $evaluations[2];
        $playlist = $evaluations[3];
        $usi_futuri = $evaluations[4];
      
        $results=array();
        
        $media = mean($novelty);
        $dev_standard = stats_standard_deviation($novelty);
        $moda = moda($novelty);
        array_push($results, array("Novelty"=>array("Media"=>$media, "Dev Standard"=>$dev_standard, "Moda"=>$moda)));
        
        $media = mean($serendipity);
        $dev_standard = stats_standard_deviation($serendipity);
        $moda = moda($serendipity);
        array_push($results, array("Serendipity"=>array("Media"=>$media, "Dev Standard"=>$dev_standard, "Moda"=>$moda)));
        
        $media = mean($diversity);
        $dev_standard = stats_standard_deviation($diversity);
        $moda = moda($diversity);
        array_push($results, array("Diversity"=>array("Media"=>$media, "Dev Standard"=>$dev_standard, "Moda"=>$moda)));
        
        $media = mean($playlist);
        $dev_standard = stats_standard_deviation($playlist);
        $moda = moda($playlist);
        array_push($results, array("Playlist"=>array("Media"=>$media, "Dev Standard"=>$dev_standard, "Moda"=>$moda)));
        
        $media = mean($usi_futuri);
        $dev_standard = stats_standard_deviation($usi_futuri);
        $moda = moda($usi_futuri);
        array_push($results, array("Usi Futuri"=>array("Media"=>$media, "Dev Standard"=>$dev_standard, "Moda"=>$moda)));
        
        return $results;
    }
    
    function mean($values){
        return (array_sum($values)/count($values));
    }
    
    function stats_standard_deviation(array $a, $sample = false) {
        $n = count($a);
        if ($n === 0) {
            trigger_error("The array has zero elements", E_USER_WARNING);
            return false;
        }
        if ($sample && $n === 1) {
            trigger_error("The array has only 1 element", E_USER_WARNING);
            return false;
        }
        $mean = array_sum($a) / $n;
        $carry = 0.0;
        foreach ($a as $val) {
            $d = ((double) $val) - $mean;
            $carry += $d * $d;
        };
        if ($sample) {
            --$n;
        }
        return sqrt($carry / $n);
    }
    
    function moda($values){
        $map = array("1"=>0,"2"=>0, "3"=>0, "4"=>0, "5"=>0);
        foreach($values as $value){
            switch($value) {
                case 1:
                    $temp=$map["1"];
                    $temp++;
                    $map["1"]=$temp;
                    break;
                case 2:
                    $temp=$map["2"];
                    $temp++;
                    $map["2"]=$temp;
                    break;
                case 3:
                    $temp=$map["3"];
                    $temp++;
                    $map["3"]=$temp;
                    break;
                case 4:
                    $temp=$map["4"];
                    $temp++;
                    $map["4"]=$temp;
                    break;
                case 5:
                    $temp=$map["5"];
                    $temp++;
                    $map["5"]=$temp;
                    break;
            }
        }
        arsort($map, SORT_NUMERIC);
        return array_keys($map)[0];
    }
?>