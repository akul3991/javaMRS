<?php
    define('DB_SERVER', 'personalitymrs.it.mysql');
    define('DB_USERNAME', 'personalitymrs_');    // DB username
    define('DB_PASSWORD', 'uzyGHTx2');    // DB password
    define('DB_DATABASE', 'personalitymrs_');      // DB name
    $connection = mysql_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD) or die( "Unable to connect");
    $database = mysql_select_db(DB_DATABASE) or die( "Unable to select database");
?>