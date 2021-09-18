#!/bin/zsh

# Timestamp the start of the script
startTime=$(date +%s)
for i in {1..100}
do
   http GET localhost:8080/posts/
done

# Timestamp the end of the script
endTime=$(date +%s)
totalTime=$(${startTime} - ${endTime})

printf "Start Time: ${startTime}"
printf "End Time: ${endTime}"
prtintf "Total Time: ${totalTime}"

# Output
# ./scratch.sh  28.11s user 6.90s system 71% cpu 49.310 total

