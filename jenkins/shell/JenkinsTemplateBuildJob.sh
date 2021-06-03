#!/usr/bin/env bash

# Exit on error.
set -o errexit
# Exit on error inside any functions or subshells.
set -o errtrace
# Catch the errors eg. in case mysqldump fails (but gzip succeeds) in `mysqldump |gzip`
set -o pipefail
# Turn on traces, useful while debugging but commented out by default
set -o xtrace


#####
# Echo's the verion.py file
#####
do_something(){
  echo "I am the build job..."
}


#####
# Main Method
#####
main(){
  do_something
}


# Pass control to our main Method
main "$@"
