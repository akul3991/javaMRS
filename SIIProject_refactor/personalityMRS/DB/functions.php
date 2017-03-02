<?php
    require 'dbconfig.php';

    function checkuser($fuid,$ffname,$femail,$uid){
        
        $ffname = preg_replace("/\'/","\'", $ffname);
        mysql_query("SET NAMES UTF8");
        
        $check = mysql_query("select * from Users where Fuid='$fuid'");
        $check = mysql_num_rows($check);
        
        $check2 = mysql_query("select * from Users where Ffname='$ffname'");
        $check2 = mysql_num_rows($check2);
        
        $check3 = mysql_query("select Fuid from Users where Ffname='$ffname'");
        
        $checkFUID = mysql_fetch_array($check3, MYSQL_NUM)[0];
        
        $check4 = mysql_query("select UID from Users where Ffname='$ffname'");
        $check4= mysql_num_rows($check4);
        
        
        //controllo dovuto all'inserimento anticipato di dati nelle tabelle
        if( !(empty($check2))&& ($checkFUID=="")){
            mysql_query("UPDATE Users SET Fuid='$fuid', Femail='$femail' where Ffname='$ffname'");
        }elseif(empty($check)){
            mysql_query("INSERT INTO Users (UID, Fuid,Ffname,Femail) VALUES ('$uid','$fuid','$ffname','$femail')");
        }
    }
    
    
    function addPersonality($fuid, $b5c, $b5a, $b5o, $b5n, $b5e){
        
        $check = mysql_query("select * from PersonalityFB where Fuid='$fuid'");
        $check = mysql_num_rows($check);
        if (empty($check)) { // if not personality  . Insert a new record
            $query = "INSERT INTO PersonalityFB (Fuid,ope,neu,ext,con,agr) VALUES ('$fuid', '$b5o','$b5n', '$b5e', '$b5c', '$b5a' )";
            mysql_query($query);
        }
    }
    
    //Find personality traits of new user u and LastFmUser v
    function findPersonality($uid, $FB){
        
        if($FB){
            $result_u = mysql_query("SELECT ope,neu,ext,con,agr FROM PersonalityFB WHERE Fuid='$uid'");
        }else{
            $result_u = mysql_query("SELECT ope,neu,ext,con,agr FROM PersonalityQUEST WHERE userid='$uid'");
        }
        $u = mysql_fetch_array($result_u, MYSQL_NUM);
        return $u;
    }
    
    //List of LastFM userid
    function findUsersLFM(){
        $userLFM = array();
        $result = mysql_query("SELECT * FROM Big5LFM");
        while($row = mysql_fetch_array($result, MYSQL_NUM)){
            $userLFM[$row[0]] = array($row[1],$row[2],$row[3],$row[4],$row[5]);
        }
        return $userLFM;
    }
    
    function selectTracks($v, $n){
        $tracks = array();
        
        $query = mysql_query("SELECT DISTINCT Name, Title, TrackID preferences FROM (SELECT track, preferences FROM PreferencesLFM WHERE user='$v' ORDER BY preferences DESC LIMIT $n) AS t1 JOIN (TracksLFM,ArtistLFM) ON (t1.track = TracksLFM.TrackID AND TracksLFM.Artist = ArtistLFM.ArtistID ) ");
        
        while($row = mysql_fetch_array($query, MYSQL_NUM)){
            
            $tracks[$row[2]]= [preg_replace("/\&.*/", "", $row[0]) => preg_replace("/\(.*\)/", "", $row[1])];
            
            
        }
        return $tracks;
    }
    
    
    function getUID($fid){
        $result = mysql_query("SELECT UID FROM Users WHERE Fuid = '$fid'");
        while($row = mysql_fetch_array($result, MYSQL_NUM)){
            $uid =$row[0];
        }
        return $uid;
    }
    
    
    function addAnswersBIG5test($uid,$sex,$age, $nationality, $work, $education, $q1,$q2,$q3,$q4,$q5,$q6,$q7,$q8,$q9,$q10,$q11,$q12,$q13,$q14,$q15,$q16,$q17,$q18,$q19,$q20,$q21,$q22,$q23,$q24,$q24,$q25,$q26,$q27,$q28,$q29,$q30,$q31,$q32,$q33,$q34,$q35,$q36,$q37,$q38,$q39,$q40,$q41,$q42,$q43,$q44){
        mysql_query("SET NAMES UTF8");

        $query = "INSERT INTO Questionnaire (UID, Sex, Age, Nationality, Work, Education, q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20, q21, q22, q23, q24, q25, q26, q27, q28, q29, q30, q31, q32, q33, q34, q35, q36, q37, q38, q39, q40, q41, q42, q43, q44) VALUES ('$uid','$sex', '$age', '$nationality', '$work', '$education', '$q1', '$q2', '$q3', '$q4', '$q5', '$q6','$q7','$q8','$q9','$q10','$q11','$q12','$q13', '$q14','$q15','$q16','$q17','$q18','$q19','$q20','$q21','$q22','$q23','$q24','$q25','$q26','$q27','$q28','$q29','$q30','$q31','$q32','$q33', '$q34','$q35','$q36','$q37','$q38','$q39','$q40','$q41','$q42','$q43','$q44')";
        
        mysql_query($query);
        /*if(!$query)
         die('Could not update data: ' . mysql_error());
         else
         echo "Updated data successfully\n";*/
    }
    
    
    function addPersonalityBIG5($uid, $ope, $neu, $ext, $con, $agr){
        
        $query = "INSERT INTO PersonalityQUEST (userid, ope, neu, ext, con, agr) VALUES ('$uid', '$ope', '$neu', '$ext', '$con', '$agr')";
        mysql_query($query);
        
    }
    
    
    
    function checkQuestIsEmpty($uid){
        
        $query = "SELECT * FROM PersonalityQUEST where userid='$uid' ";
        $check = mysql_num_rows(mysql_query($query));
        
        return (empty($check));
    }
    
    function getTraits($uid){
        $query = mysql_query("SELECT ope, neu, ext, con, agr FROM PersonalityQUEST where userid='$uid' ");
        $row = mysql_fetch_array($query, MYSQL_NUM);
        $traits['ope'] = $row[0];
        $traits['neu'] = $row[1];
        $traits['ext'] = $row[2];
        $traits['con'] = $row[3];
        $traits['agr'] = $row[4];
        
        return $traits;
    }
    
    function getGender($uid){
        $result = mysql_query("SELECT Sex FROM Questionnaire WHERE UID = '$uid'");
        $row = mysql_fetch_array($result, MYSQL_NUM);
        $gender =$row[0];
        return $gender;
    }
    
    
    function checkLibraryIsEmpty($uid){
        
        $query = "SELECT * FROM LibraryClassic where UID='$uid' ";
        $check = mysql_num_rows(mysql_query($query));
        
        return (empty($check));
    }
    
    
    
    function addRecommendedSongs($songID, $uid, $FB){
        
        if($FB)
            mysql_query("INSERT INTO RecommendedSongsFB (UID, TrackID) VALUES ('$uid', '$songID')");
        
        else
            mysql_query("INSERT INTO RecommendedSongsQUEST (UID, TrackID) VALUES ('$uid', '$songID')");
    }
    
    
    function addRecommendedSongsByGenres($songID, $uid){
        mysql_query("INSERT INTO RecommendedSongByGenres (UID, TrackID) VALUES ('$uid', '$songID')");
    }
    
    
    function NOTexistsRecommendation($u,$FB){
        if($FB){
            $check = mysql_query("select * from RecommendedSongsFB where UID='$u'");
            $check = mysql_num_rows($check);
        }else{
            $check = mysql_query("select * from RecommendedSongsQUEST where UID='$u'");
            $check = mysql_num_rows($check);
        }
        return empty($check);
    }
    
    function retrieveRecommendedSong($u,$FB){
        $tracks = array();
        $artist= array();
        $track= array();
        
        if($FB){
            $query = mysql_query("SELECT DISTINCT Name, Title FROM (SELECT TrackID FROM RecommendedSongsFB WHERE UID='$u' ORDER BY Time DESC ) AS t1 JOIN (Tracks,Artist) ON (t1.TrackID = Tracks.TrackID AND Tracks.Artist = Artist.ArtistID)");
            while($row = mysql_fetch_array($query, MYSQL_NUM)){
                $row[0] = preg_replace("/\\'/","'",$row[0]);
                $row[1] = preg_replace("/\\'/","'",$row[1]);
                array_push($artist, preg_replace("/\&.*/", "", $row[0]));
                array_push($track, $row[1]);
            }
            $tracks = array_map(null,$artist, $track);
            
        }else{
            
            $query = mysql_query("SELECT DISTINCT Name, Title FROM (SELECT TrackID FROM RecommendedSongsQUEST WHERE UID='$u' ORDER BY Time DESC) AS t1 JOIN (Tracks,Artist) ON (t1.TrackID = Tracks.TrackID AND Tracks.Artist = Artist.ArtistID)");
            while($row = mysql_fetch_array($query, MYSQL_NUM)){
                $row[0] = preg_replace("/\\'/","'",$row[0]);
                $row[1] = preg_replace("/\\'/","'",$row[1]);
                array_push($artist, preg_replace("/\&.*/", "", $row[0]));
                array_push($track, $row[1]);
            }
            $tracks = array_map(null,$artist, $track);
        }
        
        return $tracks;
        
    }
    
    function retrieveLibrarySong($u){
        $tracks = array();
        $artist= array();
        $track= array();
        $query = mysql_query("SELECT DISTINCT Name, Title FROM (SELECT TrackID FROM LibraryClassic WHERE UID='$u' ORDER BY Time DESC) AS t1 JOIN (Tracks,Artist) ON (t1.TrackID = Tracks.TrackID AND Tracks.Artist = Artist.ArtistID)");
        while($row = mysql_fetch_array($query, MYSQL_NUM)){
            $row[0] = preg_replace("/\\'/","'",$row[0]);
            $row[1] = preg_replace("/\\'/","'",$row[1]);
            array_push($artist, preg_replace("/\&.*/", "", $row[0]));
            array_push($track, $row[1]);
        }
        $tracks = array_map(null,$artist, $track);
        return $tracks;
    }
    
    
    function addLibrary($uid, $trackId){
        mysql_query("INSERT INTO LibraryClassic (UID, TrackID) VALUES ('$uid', '$trackId')");
    }
    
    
    
    function addTracksAndArtist($artistID, $artistName, $trackId, $title){
        
        $artistName = preg_replace("/\'/","\'",$artistName);
        $title = preg_replace("/\'/","\'",$title);
        
        mysql_query("SET NAMES UTF8");
        
        $query2 = "INSERT INTO Artist (ArtistID, Name) VALUES ('$artistID', '$artistName')";
        mysql_query($query2);
        
        $query = "INSERT INTO Tracks (TrackID, Title, Artist) VALUES ('$trackId', '$title', '$artistID')";
        mysql_query($query);
    }
    
    
    function addEvaluation($uid, $typeRec, $q1, $q2, $q3, $q4, $q5, $feedback){
        $feedback = preg_replace("/\'/","\'", $feedback);
        mysql_query("SET NAMES UTF8");

        if($typeRec=="AMS")
            mysql_query("INSERT INTO EvaluationsFB(UID, q1, q2, q3, q4, q5, feedback) VALUES ('$uid','$q1','$q2','$q3','$q4','$q5', '$feedback')");
        elseif($typeRec=="QUEST")
            mysql_query("INSERT INTO EvaluationsQUEST(UID, q1, q2, q3, q4, q5, feedback) VALUES ('$uid','$q1','$q2','$q3','$q4','$q5','$feedback')");
        elseif($typeRec=="CLASSIC")
            mysql_query("INSERT INTO EvaluationsCLASSIC(UID, q1, q2, q3, q4,q5, feedback) VALUES ('$uid','$q1','$q2','$q3','$q4','$q5','$feedback')");
        else
            mysql_query("INSERT INTO EvaluationsGENRES(UID, q1, q2, q3, q4,q5, feedback) VALUES ('$uid','$q1','$q2','$q3','$q4','$q5','$feedback')");
        
         //die('Could not update data: fee' .$feedback.mysql_error());
    }
    
    function notHasSurvayFinal($uid, $typeRec){
        
        if($typeRec=="AMS")
            $query = mysql_query("SELECT * FROM EvaluationsFB WHERE UID='$uid'");
        elseif($typeRec=="QUEST")
            $query = mysql_query("SELECT * FROM EvaluationsQUEST WHERE UID='$uid'");
        elseif($typeRec=="CLASSIC")
            $query = mysql_query("SELECT * FROM EvaluationsCLASSIC WHERE UID='$uid'");
        else
            $query = mysql_query("SELECT * FROM EvaluationsGENRES WHERE UID='$uid'");
        
        $query = mysql_num_rows($query);
        
        return empty($query);
        
    }
    
    function retrieveEvaluation($typeRec){
        
        $answers1 =array();
        $answers2 =array();
        $answers3 =array();
        $answers4 =array();
        $answers5 =array();
        $answers6 =array();
        
        if($typeRec=="AMS")
            $query = mysql_query("SELECT * FROM EvaluationsFB'");
        elseif($typeRec=="QUEST")
            $query = mysql_query("SELECT * FROM EvaluationsQUEST");
        elseif($typeRec=="CLASSIC")
            $query = mysql_query("SELECT * FROM EvaluationsCLASSIC");
        else
            $query = mysql_query("SELECT * FROM EvaluationsGENRES");
        
        while($row = mysql_fetch_array($query, MYSQL_NUM)){
           
            array_push($answers1, $row[1]);
            array_push($answers2, $row[2]);
            array_push($answers3, $row[3]);
            array_push($answers4, $row[4]);
            array_push($answers5, $row[5]);
            if($row[6]!="")
                array_push($answers6, $row[6]);
        }
        $evaluations = array($answers1,$answers2,$answers3,$answers4,$answers5, $answers6);
        return $evaluations;
    }
?>
