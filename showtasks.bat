
call runcrud.bat
IF "%ERRORLEVEL%" == 0   goto startweb
echo.
echo wystapil blad so pause
pause

:startweb
start chrome "http://localhost:8080/crud/v1/task/tasks"




