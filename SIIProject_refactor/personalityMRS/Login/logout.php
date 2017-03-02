<?php 
    session_start();
    session_unset();
    $_SESSION['FBID'] = NULL;
    $_SESSION['FULLNAME'] = NULL;
    $_SESSION['EMAIL'] =  NULL;
    $_SESSION['TOKEN'] = NULL;
    $_SESSION['UID'] = NULL;
    header("Location: ../index.php");
?>
