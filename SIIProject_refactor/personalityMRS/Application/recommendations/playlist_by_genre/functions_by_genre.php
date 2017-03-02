<?php
    function getGenres($uid){
        
        $gender=getGender($uid);
        $big5Traits = getTraits($uid);
        $genres= array();
        
        $ope = $big5Traits['ope'];
        $con = $big5Traits['con'];
        $ext = $big5Traits['ext'];
        $agr = $big5Traits['agr'];
        $neu = $big5Traits['neu'];
        
        if(belong($con, 3.64, 3.76) && belong($ext, 3.635, 3.774) && belong($agr, 3.598, 3.731)){
            array_push($genres,"jazz");
        }elseif(belong($ope, 3.75, 3.875) && belong($agr,3.465, 3.598)){
            array_push($genres,"reggae");
        }elseif(belong($con, 3.64, 3.76) && belong($ext, 3.913, 4.052)){
            array_push($genres,"salsa");
        }elseif(belong($ope, 3.625, 3.75) && belong($con, 3.4, 3.52) && belong($ext, 3.496, 3.635)){
            array_push($genres,"country");
        }elseif(belong($ext, 3.635, 3.774) && belong($agr, 3.598, 3.731)&& belong($neu, 2.49, 2.61)){
            array_push($genres,"jazz");
        }elseif(belong($ext, 3.218, 3.357) && belong($neu, 2.97, 3.09)){
            array_push($genres,"metal");
        }
        
        if($gender=="F"){
            if(belong($ope, 3.625, 3.75) && belong($ext, 3.496, 3.635) && belong($neu, 2.49, 2.61)){
                if(!in_array("country",$genres))
                    array_push($genres,"country");
            }elseif(belong($ope, 4.25, 4.375) && belong($con, 3.42, 3.57)){
                array_push($genres,"classic");
            }elseif(belong($ope, 3.625, 3.75) && belong($con, 3.42, 3.57) && belong($ext, 2.49, 2.61)){
                if(!in_array("country",$genres))
                    array_push($genres,"country");
            }elseif(belong($ope, 3.625, 3.75) && belong($con, 3.42, 3.57) && belong($agr, 3.652, 3.789)){
                if(!in_array("country",$genres))
                    array_push($genres,"country");
            }elseif(belong($ope, 3.375, 3.5) && belong($neu, 2.91, 3.058)){
                array_push($genres,"r&b");
            }
        }else{
            if (belong($agr, 1, 2.915)){
                array_push($genres,"r&b");
            }elseif(belong($ope, 3.2, 3.35) && belong($ext, 3.47, 3.625) && belong($neu, 2.618, 2.776)){
                array_push($genres,"rap");
            }elseif(belong($ope, 3.35, 3.525) && belong($con,2.9, 3.05)){
                array_push($genres,"pop");
            }elseif(belong($ope, 3.7, 3.875) && belong($con, 3.35, 3.5) && belong(agr, 3.41, 3.575)){
                if(!in_array("country",$genres))
                    array_push($genres,"country");
            }elseif(belong($ope, 3.35, 3.5) && belong($agr, 3.41, 3.575) && belong($neu, 2.144, 2.302)){
                if(!in_array("country",$genres))
                    array_push($genres,"country");
            }elseif(belong($ope, 3.875, 4.05) && belong($agr, 2.915, 3.08)){
                if(!in_array("metal",$genres))
                    array_push($genres,"metal");
            }elseif(belong($con, 2.9, 3.05) && belong($agr, 2.915, 3.08)){
                if(!in_array("metal",$genres))
                    array_push($genres,"metal");
            }
        }
        
        if(sizeof($genres)==0){
            
            $max = max($ope, $con, $ext, $agr, $neu);
            $min = min($ope, $con, $ext, $agr, $neu);
            if ($min-1 < 5-$max)
                $traits = array_keys($big5Traits, $min); //chiavi con il valore min
            else if ($min-1 > 5-$max)
                $traits = array_keys($big5Traits, $max); //chiavi con il valore max
            else
                $traits = array_keys($big5Traits, $min); //chiavi con il valore max e min
            foreach($traits as $trait) {
                switch ($trait) {
                    case "ope":
                        if(belong2($ope, 1, 3)){

                            if(!in_array("r&b",$genres))
                                array_push($genres,"r&b");
                            if(!in_array("rap",$genres))
                                array_push($genres,"rap");
                            if(!in_array("hip-hop",$genres))
                                array_push($genres,"hip-hop");
                        }else{

                            if(!in_array("blues",$genres))
                                array_push($genres,"blues");
                            if(!in_array("classic",$genres))
                                array_push($genres,"classic");
                            if(!in_array("indi",$genres))
                                array_push($genres,"indi");
                        }
                        break;
                    case "con":
                        if(belong2($con, 1, 3)){

                            if(!in_array("indi",$genres))
                                array_push($genres,"indi");
                            if(!in_array("metal",$genres))
                                array_push($genres,"metal");
                            if(!in_array("tecno",$genres))
                                array_push($genres,"tecno");
                            if(!in_array("rap",$genres))
                                array_push($genres,"rap");
                        }else{

                            if(!in_array("country",$genres))
                                array_push($genres,"country");
                            if(!in_array("jazz",$genres))
                                array_push($genres,"jazz");
                            if(!in_array("salsa",$genres))
                                array_push($genres,"salsa");
                            if(!in_array("r&b",$genres))
                                array_push($genres,"r&b");
                        }
                        break;
                    case "ext":
                        if(belong2($ext, 1, 3)){

                            if(!in_array("metal",$genres))
                                array_push($genres,"metal");
                            if(!in_array("tecno",$genres))
                                array_push($genres,"tecno");
                            if(!in_array("rock",$genres))
                                array_push($genres,"rock");
                        }else{

                            if(!in_array("salsa",$genres))
                                array_push($genres,"salsa");
                            if(!in_array("hip-hop",$genres))
                                array_push($genres,"hip-hop");
                            if(!in_array("rap",$genres))
                                array_push($genres,"rap");
                        }
                        break;
                    case "agr":
                        if(belong2($agr, 1, 3)){

                            if(!in_array("metal",$genres))
                                array_push($genres,"metal");
                            if(!in_array("rap",$genres))
                                array_push($genres,"rap");
                            if(!in_array("indi",$genres))
                                array_push($genres,"indi");
                        }else{

                            if(!in_array("indi",$genres))
                                array_push($genres,"indi");
                            if(!in_array("oldies",$genres))
                                array_push($genres,"oldies");
                            if(!in_array("dance",$genres))
                                array_push($genres,"dance");
                            if(!in_array("jazz",$genres))
                                array_push($genres,"jazz");
                            
                        }
                        break;
                    case "neu":
                        if(belong2($neu, 1, 3)){

                            if(!in_array("salsa",$genres))
                                array_push($genres,"salsa");
                            if(!in_array("jazz",$genres))
                                array_push($genres,"jazz");
                            if(!in_array("hip-hop",$genres))
                                array_push($genres,"hip-hop");
                        }else{

                            if(!in_array("indi",$genres))
                                array_push($genres,"indi");
                            if(!in_array("metal",$genres))
                                array_push($genres,"metal");
                            if(!in_array("rock",$genres))
                                array_push($genres,"rock");
                        }
                        break;
                }
            }
        }

        return $genres;
    }
    
    function belong($n, $n1, $n2){
        if ($n > $n1 && $n <= $n2){
            return true;
        }
        else return false;
    }
    
    function belong2($n, $n1, $n2){
        if ($n >= $n1 && $n < $n2){
            return true;
        }
        else return false;
    }
?>