
jQuery.ajaxSettings.traditional = true;
var config = getConfig();


function findPreview(artists2songs){
    var songs = [];
    var cont= 1;
    var title_empty=0;
    for(var elem in artists2songs){
        var artist = artists2songs[elem][0];
        var title = artists2songs[elem][1];
        var url = config.echoNestHost + 'api/v4/song/search?';
        if(title!=""){
    $.getJSON(url, { 'artist': artist,
                      'title': title,
                      'api_key': config.apiKey,
                      'bucket': [ 'id:' + config.spotifySpace, 'tracks'], 'limit' : true})
                .done(function(data) {
                      if ('songs' in data.response) {
                      songs.push(data.response.songs);
                      if(cont==artists2songs.length-title_empty){
                            songs = songs.filter(function(n){ return n.length != 0});
                            fetchArtistForQuest(songs);
                    }
                    cont++;
                }
            })
        }else{
            title_empty++;
        }
    }
}

function fetchArtistForQuest(songs) {
        $("#all_results").empty();
        //info("Creating the playlist ...");
    
        getSpotifyPlayer(songs, function(player) {
                                     
        $("#all_results").append(player);});

}

function info(txt) {
    $("#info").text(txt);
}

