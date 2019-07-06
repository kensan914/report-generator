@echo off
cd %~1
cmd /c start /wait bash -c "script -q compile.log -c 'gcc %2 -o a.out'"
