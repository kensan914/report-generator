@echo off
cd %~1
cmd /c start /wait bash -c "script -q output.log -c './a.out %2 %3 %4 %5 %6 %7 %8 %9'"
