#!/usr/bin/env bash

cd `dirname $0`
script -q output.log ./a.out $@
rm a.out
rm cla.sh
PIDarr=(`ps aux | grep Terminal | grep -v grep | awk '{ print $2 }'`)
echo ${PIDarr[@]} | awk '{
    max = $1
    for(i = 2; i <= NF; i++) max = $i > max ? $i : max;
    print "kill -9", max
}' |sh
