

## Spotify

Get basic auth token by logging into spotify 
```
https://open.spotify.com
```

## Deezer

- Create oauth app
```
https://developers.deezer.com/guidelines/getting_started
```

- Get **oauth code** using appId, redirectUri and permissions [manage_library]
```
example https://connect.deezer.com/oauth/auth.php?app_id=318462&redirect_uri=https://www.google.com/&perms=basic_access,manage_library,email
```

- Get **oauth access_token** with **oauth code** and your appId+secret
```
https://connect.deezer.com/oauth/access_token.php?app_id=318462&secret=d8a9a4271d467100fd2f97d64d75fd6f&code=fr7cae8c4374f3b895c1e98e901fa6a7
```