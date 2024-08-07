#!/bin/bash

# 사용방법 예시ㅋ
# bash auto-make.sh 2 7 learn-kt/src/main/kotlin/boki/learn/debug/checkpoint/CheckPointEx.kt

# Check if the correct number of arguments is provided
if [ "$#" -ne 3 ]; then
  echo "Usage: $0 from to filename"
  exit 1
fi

# Assign input arguments to variables
from=$1
to=$2
filename=$3

# Extract the base name and extension from the provided filename
base_name="${filename%.*}"
extension="${filename##*.}"

# Loop from 'from' to 'to'
for i in $(seq $from $to)
do
  # Construct the new filename with the number inserted before the extension
  new_filename="${base_name}$i.$extension"

  # Create the file
  touch "$new_filename"
done

echo "Files ${base_name}${from}.${extension} to ${base_name}${to}.${extension} have been created."
