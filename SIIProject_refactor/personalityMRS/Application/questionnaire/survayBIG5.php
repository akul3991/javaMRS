<?php
    session_start();
    require_once("../../DB/functions.php");

    $questions = array("&egrave loquace","tende a trovare da ridire sugli altri","lavora in modo accurato","&egrave depressa, triste", "&egrave originale, propone idee nuove","&egrave riservata","&egrave premurosa ed altruista con gli altri","pu&ograve essere piuttosto sbadata","&egrave rilassata, gestisce bene lo stress","ha curiosit&agrave in molti ambiti diversi","&egrave piena di energia","attacca briga con gli altri","&egrave un lavoratore affidabile","pu&ograve essere tesa","&egrave ingegnosa, un pensatore profondo","genera molto entusiasmo","per natura tende a perdonare","tende ad essere disorganizzata","si preoccupa molto","ha un'immaginazione attiva","tende ad essere taciturna","di solito si fida","tende ad essere pigra","&egrave emotivamente stabile, non si turba facilmente","&egrave inventiva","ha una personalit&agrave energica","pu&ograve essere fredda ed emotivamente distaccata","persevera finch&eacute il compito &egrave terminato","pu&ograve essere lunatica","apprezza le esperienze artistiche, estetiche","&egrave qualche volta timida, inibita","&egrave premurosa e gentile pressoch&eacute con tutti","fa le cose efficientemente","rimane calma nelle situazioni tese","preferisce un lavoro che sia di routine","&egrave estroversa, socievole","&egrave qualche volta scortese con gli altri","fa dei piani e li porta a termine","diventa facilmente apprensiva","ama riflettere, giocare con le idee","ha pochi interessi artistici","ama cooperare con gli altri","&egrave facilmente distratta","ha una sensibilit&agrave raffinata per l'arte, la musica o la letteratura");
    $resultBIG5=array();
    $submitOK = 0;
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        
        $check=1;
        if ($_POST["age"]=="NoAnsw") {
            $age = $_POST["age"];
            $ageErr = "Il campo è obbligatorio";
            $submitOK = 1;
        } else {
            $age = $_POST["age"];
            $ageErr = "";
        }
        
        if (empty($_POST["gender"])) {
            $genderErr = "Il campo è obbligatorio";
            $submitOK = 1;
        } else {
            $gender = test_input($_POST["gender"]);
            $genderErr = "";
        }

        if (empty($_POST["nation"])) {
            $nationErr = "Il campo è obbligatorio";
            $submitOK = 1;
        } else {
            $nation= test_input($_POST["nation"]);
            $nationErr = "";
        }
        
        if ($_POST["profession"]=="NoAnsw") {
            $profession = $_POST["profession"];
            $profErr = "Il campo è obbligatorio";
            $submitOK = 1;
        } else {
            $profession = $_POST["profession"];
            $profErr = "";
        }
        
        if ($_POST["education"]=="NoAnsw") {
            $education = $_POST["education"];
            $eduErr = "Il campo è obbligatorio";
            $submitOK = 1;
        } else {
            $education = $_POST["education"];
            $eduErr = "";
        }
        
        
        for ($i=1; $i<=44; $i++) {
            if (empty($_POST["answer".$i])) {
                ${'answer'.$i} = "";
                $submitOK = 1;
            } else {
                ${'answer'.$i} = $_POST["answer".$i];
            }
        }
        
        if($submitOK == 0){
            for($i=1; $i<=44; $i++) {
                array_push($resultBIG5, ${'answer'.$i});
            }
            $_SESSION['AnswerBIG5']=$resultBIG5;
            $_SESSION['AGE']=$age;
            $_SESSION['GENDER']=$gender;
            $_SESSION['NATION']=$nation;
            $_SESSION['PROFESSION']=$profession;
            $_SESSION['EDUCATION']=$education;
            header("Location: ../predictPersonality/resultSurvayBIG5.php");
            return;
        }
        
    } else {
        $check = 0;
        $ageErr = $genderErr = $eduErr = $profErr = $nationErr = "";
        $age = $gender = $education = $profession = $nation = "";
        
        for($i=1; $i<=44; $i++) {
            ${'answer'.$i} = "";
        }
    }
    
    function test_input($data) {
        $data = trim($data);
        $data = stripslashes($data);
        $data = htmlspecialchars($data);
        return $data;
    }
    
    function checkError($ans, $control) {
        if ($ans=="" && $control!=0) {
            return "Il campo è obbligatorio";
        } else {
            return "";
        }
    }
?>

<!DOCTYPE HTML>

<html>
    <head>
        <link href="http://www.bootstrapcdn.com/twitter-bootstrap/2.2.2/css/bootstrap-combined.min.css" rel="stylesheet">
        <link type="text/css" href="https://netdna.bootstrapcdn.com/bootswatch/3.1.1/flatly/bootstrap.min.css" rel="stylesheet">

        <link type="text/css" href="../../CSS/styles.css" rel="stylesheet" />
    </head>
    <body id="gallery">
    
    <h2>Questionario BIG5 sulla personalit&agrave</h2>
    <h4>Ad ogni domanda sono associate 5 risposte, che vanno dall'essere <i>In disaccordo fortemente</i>, all'essere  <i>D'accordo fortemente</i>: tu ne dovrai indicare una sola in base a quanto sei o no d'accordo con l'affermazione che si fa nella domanda. Es. "Io mi vedo una persona che...  è loquace".<br>
    Una volta compilato il questionario verrai rimandato alla tua homepage, dove ti verr&agrave mostrata una playlist personalizzata basata sulle tue risposte.
    </h4>
                
    <br>
    <center><p><span class="error">* campo obbligatorio</span></p></center>

    <form id="form1" method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">
    
   <center> 
   
        Et&agrave: 
        <select name="age" width="80" style="width: 80px">
            <option value="NoAnsw"  <?php if (isset($age) && $age=="NoAnsw") echo "selected='selected'";?>></option>
            <option value="0-18"  <?php if (isset($age) && $age=="0-18") echo "selected='selected'";?>>0-18</option>
            <option value="19-24"  <?php if (isset($age) && $age=="19-24") echo "selected='selected'";?>>18-24</option>
            <option value="25-29"  <?php if (isset($age) && $age=="25-29") echo "selected='selected'";?>>25-29</option>
            <option value="30-35"  <?php if (isset($age) && $age=="30-35") echo "selected='selected'";?>>30-35</option>
            <option value="36-45"  <?php if (isset($age) && $age=="36-45") echo "selected='selected'";?>>36-45</option>
            <option value="46-55"  <?php if (isset($age) && $age=="46-55") echo "selected='selected'";?>>46-55</option>
            <option value="56-oltre"  <?php if (isset($age) && $age=="56-oltre") echo "selected='selected'";?>>56-oltre</option>
        </select>
        <span class="error">* <?php echo $ageErr;?></span>
        <br><br>
    
        Sesso:
                    <input type="radio" name="gender" <?php if (isset($gender) && $gender=="F") echo "checked";?>  value="F">F
                    <input type="radio" name="gender" <?php if (isset($gender) && $gender=="M") echo "checked";?>  value="M">M
                    <span class="error">* <?php echo $genderErr;?></span>
                    <br><br>
                    
        Nazionalità:
                    <input type="radio" name="nation" <?php if (isset($nation) && $nation=="ITA") echo "checked";?>  value="ITA">Italiana
                    <input type="radio" name="nation" <?php if (isset($nation) && $nation=="NO ITA") echo "checked";?>  value="NO ITA">Altro
                    <span class="error">* <?php echo $nationErr;?></span>
                    <br><br>
                    
        Professione:   
        <select  name="profession"width="180" style="width: 180px">
                <option value="NoAnsw" <?php if (isset($profession) && $profession=="NoAnsw") echo "selected='selected'";?>></option>
                <option value="Disoccupato" <?php if (isset($profession) && $profession=="Disoccupato") echo "selected='selected'";?>>Disoccupato/a</option>
                <option value="Studente" <?php if (isset($profession) && $profession=="Studente") echo "selected='selected'";?>>Studente</option>
                <option value="Impiegato" <?php if (isset($profession) && $profession=="Impiegato") echo "selected='selected'";?>>Impiegato/a</option>
                <option value="Libero Professionista" <?php if (isset($profession) && $profession=="Libero Professionista") echo "selected='selected'";?>>Libero Professionista</option>
                <option value="Casalingo" <?php if (isset($profession) && $profession=="Casalingo") echo "selected='selected'";?>>Casalingo/a</option>
                <option value="Pensionato" <?php if (isset($profession) && $profession=="Pensionato") echo "selected='selected'";?>>Pensionato/a</option>
        </select>
        <span class="error">* <?php echo $profErr;?></span>
        <br><br>
        
        Titolo di studio:
        <select  name="education">
                <option value="NoAnsw" <?php if (isset($education) && $education=="NoAnsw") echo "selected='selected'";?>></option>
                <option value="Non diplomato" <?php if (isset($education) && $education=="Non diplomato") echo "selected='selected'";?>>Non diplomato</option>
                <option value="Diploma di Maturità" <?php if (isset($education) && $education=="Diploma di Maturità") echo "selected='selected'";?>>Diploma di Maturità</option>
                <option value="Triennale" <?php if (isset($education) && $education=="Triennale") echo "selected='selected'";?>>Laurea Triennale</option>
                <option value="Magistrale" <?php if (isset($education) && $education=="Magistrale") echo "selected='selected'";?>>Laurea Magistrale o (V.O)</option>
                <option value="Dottorato" <?php if (isset($education) && $education=="Dottorato") echo "selected='selected'";?>>Dottorato di Ricerca o altro (Master ecc.)</option>
        </select>
        <span class="error">* <?php echo $eduErr;?></span>
    </center>
    <br><br>
                <table id="myTable" borderColor="#51d4c2"  cellSpacing=0  width=500 border=2 height=10>
                    <TR>
                        <TD bgcolor="#DCDCDC"><p><center>In disaccordo</center><center>fortemente</center><center><b>1</b></center></p></TD>
                        <TD bgcolor="#FFFFFF"><p><center>Un p&ograve in</center><center>disaccordo</center><center><b>2</b></center></p></TD>
                        <TD bgcolor="#DCDCDC"><p><center>N&egrave d'accordo</center><center>n&egrave in disaccordo</center><center><b>3</b></center></p></TD>
                        <TD bgcolor="#FFFFFF"><p><center>Un p&ograve</center><center>d'accordo</center><center><b>4</b></center></p></TD>
                        <TD bgcolor="#DCDCDC"><p><center>D'accordo</center><center>fortemente</center><center><b>5</b></center></p></TD>
                    </TR>
                </table>
                <br>
        <h4><b>Io mi vedo una persona che...</b></h4>
                <div id="tableContainer-1">
                <div id="tableContainer-2">
                <table id="myTable">
                <?php
                for($i=1; $i<=44; $i++) {?>
                <TR>
                   <TD class="quest"><b><?php echo $questions[$i-1]?>:</b></TD>
                   <TD> <input type="radio" name=<?php echo "answer".$i ?> <?php if (isset(${'answer'.$i}) && ${'answer'.$i}==1) echo "checked";?>  value=1>1</TD>
                   <TD> <input type="radio" name=<?php echo "answer".$i ?> <?php if (isset(${'answer'.$i}) && ${'answer'.$i}==2) echo "checked";?>  value=2>2</TD>
                   <TD> <input type="radio" name=<?php echo "answer".$i ?> <?php if (isset(${'answer'.$i}) && ${'answer'.$i}==3) echo "checked";?>  value=3>3</TD>
                   <TD> <input type="radio" name=<?php echo "answer".$i ?> <?php if (isset(${'answer'.$i}) && ${'answer'.$i}==4) echo "checked";?>  value=4>4</TD>
                   <TD> <input type="radio" name=<?php echo "answer".$i ?> <?php if (isset(${'answer'.$i}) && ${'answer'.$i}==5) echo "checked";?>  value=5>5</TD>
                   <TD> <span class="error">* <?php echo checkError(${'answer'.$i}, $check);?></span></TD>
                </TR>
              <?php  }?>
                </table>
       <br><br>
    <center><input type="submit" name="submit" value="INVIO"></center>
    </form>
    </body>
</html>