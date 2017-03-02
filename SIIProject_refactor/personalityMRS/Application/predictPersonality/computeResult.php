<?php
  
    function reverseScored($answerList) {
        for ($i = 0; $i<sizeof($answerList); $i++){
            if ($i==1|$i==5|$i==7|$i==8|$i==11|$i==17|$i==20|$i==22|$i==23|$i==26|$i==30|$i==33|$i==34|$i==36|$i==40|$i==42){
                $answerList[$i] = 6-$answerList[$i];
            }
        }
        return $answerList;
    }

    function  BFIOpennessScore($answerList) {
        $newlist = array();
        for ($i = 0; $i<sizeof($answerList); $i++){
            if($i==4|$i==9|$i== 14|$i==19|$i== 24| $i==29| $i==34|$i== 39|$i== 40| $i==43){
                array_push($newlist, $answerList[$i]);
            }
        }
        return mean($newlist);
    }

    function  BFINeuroticismScore($answerList) {
        $newlist = array();
        for ($i = 0; $i<sizeof($answerList); $i++){
            if($i==3|$i==8|$i== 13|$i==18|$i== 23| $i==28| $i==33|$i== 38)
                array_push($newlist, $answerList[$i]);
        }
        return mean($newlist);
    }
    
    function  BFIConscientiousScore($answerList) {
        $newlist = array();
        for ($i = 0; $i<sizeof($answerList); $i++){
            if($i==2|$i==7|$i== 12|$i==17|$i== 22| $i==27| $i==32|$i== 37|$i== 42)
                array_push($newlist, $answerList[$i]);
        }
        return mean($newlist);
    }
    
    function  BFIAgreeablenessScore($answerList) {
        $newlist = array();
        for ($i = 0; $i<sizeof($answerList); $i++){
            if($i==1|$i==6|$i== 11|$i==16|$i== 21| $i==26| $i==31|$i== 36|$i== 41)
                array_push($newlist, $answerList[$i]);
        }
        return mean($newlist);
    }

    function  BFIExtraversionScore($answerList) {
        $newlist = array();
        for ($i = 0; $i<sizeof($answerList); $i++){
            if($i==0|$i==5|$i== 10|$i==15|$i== 20| $i==25| $i==30|$i== 35)
                array_push($newlist, $answerList[$i]);
        }
        return mean($newlist);
    }

    function mean($list) {
        $sum = 0;
        foreach($list as $d){
            $sum = $sum+$d;
        }
        return $sum/sizeof($list);
    }

  /* Ipsatize the 44BFI Items before Socring Scale */
    
 /*   function ipsatized($list){
        $listUpdate = array();
        //Compute within-person response means (bfive)
        $list32 = array();
        for ($i = 0; $i<sizeof($list); $i++){
            if($i==0|$i==5|$i==15|$i==20|$i==30|$i==35|$i==1|$i==6|$i==11|$i==16|$i==26|$i==31|
               $i==41|$i==2|$i==7|$i==12|$i==17|$i==22|$i==27|$i==32|$i==42|$i==8|$i==18|
               $i==23|$i==28|$i==33|$i==38|$i==4|$i==29|$i==34|$i==40){
                array_push($list32,$list[$i]);
            }
        }
        $bfiave = mean($list32);
        //Compute within-person response standard deviation (bfistd)
        $bfistd = sd($list32);
        foreach ($list as $bfi){
            array_push($listUpdate, ($bfi-$bfiave)/$bfistd);
        }
        return $listUpdate;
    }
    
    
    //Standard deviation
    function sd($list){
        $avg = mean($list);
        $sum = 0;
        for ($i= 0; $i<sizeof($list); $i++){
            $sum = $sum + pow($list[$i]-$avg, 2);
        }
        $variance =  $sum/(sizeof($list)-1);
        return sqrt($variance);
    }
*/
?>