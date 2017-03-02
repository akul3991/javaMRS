<?php
session_start();

require_once 'autoload.php';
require '../DB/functions.php';  
use Facebook\FacebookSession;
use Facebook\FacebookRedirectLoginHelper;
use Facebook\FacebookRequest;
use Facebook\FacebookResponse;
use Facebook\FacebookSDKException;
use Facebook\FacebookRequestException;
use Facebook\FacebookAuthorizationException;
use Facebook\GraphObject;
use Facebook\Entities\AccessToken;
use Facebook\HttpClients\FacebookCurlHttpClient;
use Facebook\HttpClients\FacebookHttpable;

// init app with app id and secret
FacebookSession::setDefaultApplication( '962642077104061','62fd7d0c143fdb7c528296cd8ed11849' );

$redirect_url = 'http://personalitymrs.it/Login/fbconfig.php';
$helper = new FacebookRedirectLoginHelper($redirect_url);

try {
    $session = $helper->getSessionFromRedirect();
} catch( FacebookRequestException $ex ) {
  // When Facebook returns an error
} catch( Exception $ex ) {
  // When validation fails or other local issues
}
// see if we have a session
if ( isset( $session ) ) {
    $acces_token = $session->getToken();
    // graph api request for user data
    $request = new FacebookRequest( $session, 'GET', '/me');
    $response = $request->execute();
    // get response
    $graphObject = $response->getGraphObject();
    $fuid = $graphObject->getProperty('id');              // To Get Facebook ID
 	$ffname = $graphObject->getProperty('name'); // To Get Facebook full name
	$femail = $graphObject->getProperty('email');    // To Get Facebook email ID
    $uid = md5($ffname.$femail);


	/* ---- Session Variables -----*/
	$_SESSION['FBID'] = $fuid;           
    $_SESSION['FULLNAME'] = $ffname;
	$_SESSION['EMAIL'] =  $femail;
    $_SESSION['TOKEN'] = $acces_token;

	checkuser($fuid,$ffname,$femail,$uid);
        
    /* ---- header location after session ----*/
    header("Location: ../index.php");
} else {
    $loginUrl = $helper->getLoginUrl(array('scope' => 'email,user_likes'));
    header("Location: ".$loginUrl);
}