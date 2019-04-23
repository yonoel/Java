:: rename.bat
set now=%date:~0,4%%date:~5,2%%date:~8,2%
ren App-1.0-SNAPSHOT.jar App-1.0-SNAPSHOT-%now%.txt