<?php
    require_once ('../../Login/autoload.php');
    use Facebook\FacebookSession;
    use Facebook\FacebookRequest;
    use Facebook\FacebookResponse;
    use Facebook\GraphObject;
    require_once('../../DB/functions.php');

    use Papi\Client\Exception\NoPredictionException;
    use Papi\Client\Model\TraitName;
    use Papi\Client\PapiClient;

    spl_autoload_register("autoload");

    $serviceUrl = "http://api-v2.applymagicsauce.com";
    $papiClient = new PapiClient($serviceUrl);

    // This is what you got during registration
    $customerId = 313;
    $apiKey = "veldo69jsjjknmik3322qnrtcb";

    // Id of Facebook user
    $uid = $_SESSION['FBID'];

    //Facebook session
    $faccess_token = $_SESSION['TOKEN'];

    FacebookSession::setDefaultApplication( '962642077104061','62fd7d0c143fdb7c528296cd8ed11849' );
    $session = new FacebookSession($faccess_token);

    // graph api request for user likes id

    $request2 = new FacebookRequest( $session, 'GET', '/me/likes?limit=3000' );
    $response2 = $request2->execute();
    $graphObject2 = $response2->getGraphObject();
    if (!empty($graphObject2->getProperty('data'))) {
        $data = $graphObject2->getProperty('data')->asArray();
        $likesIds = array();
        foreach($data as $k) {
            $item = $k->id;
            array_push($likesIds, $item);
        }
    
        // Authentication. This token will be valid for at least one hour, so you want to store it
        // and re-use for further requests
        $token = $papiClient->getAuthResource()->requestToken($customerId, $apiKey);
    
        // Get predictions and print
        try {
            $predictionResult = $papiClient->getPredictionResource()->getByLikeIds(
                array(TraitName::BIG5), $token->getTokenString(), $uid, $likesIds);
            $predictions = $predictionResult->getPredictions();
            $traits=array();
            $values=array();
            for($i=1; $i<=5; $i++){
                array_push($traits, $predictions[$i]->getTrait());
                array_push($values,$predictions[$i]->getValue());
            }
            $prediction = array_map(null,$traits,$values);
            $_SESSION['PREDICTION_FB'] = $prediction;
            $b5c= 1+(4*$prediction[0][1]);
            $b5a= 1+(4*$prediction[1][1]);
            $b5o= 1+(4*$prediction[2][1]);
            $b5n= 1+(4*$prediction[3][1]);
            $b5e= 1+(4*$prediction[4][1]);
            addPersonality($uid, $b5o,$b5n, $b5e, $b5c, $b5a  );
        } catch (NoPredictionException $e) {
            $_SESSION['PREDICTION_FB'] = " Sorry! No prediction could be made";
        }
    } else {
         $_SESSION['PREDICTION_FB'] = " Sorry! No prediction could be made";
    }
    
    function autoload($className) {
        $className = ltrim($className, "\\");
        $fileName = "";
        if ($lastNsPos = strripos($className, "\\")) {
            $namespace = substr($className, 0, $lastNsPos);
            $className = substr($className, $lastNsPos + 1);
            $fileName = str_replace("\\", DIRECTORY_SEPARATOR, $namespace) . DIRECTORY_SEPARATOR;
        }
        $fileName .= str_replace('_', DIRECTORY_SEPARATOR, $className).'.php';
        require_once $fileName;
    }
?>