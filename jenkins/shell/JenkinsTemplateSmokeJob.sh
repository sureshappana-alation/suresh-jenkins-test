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
echo_verion_file(){
  cat "${WORKSPACE}/${VERSION_FILE}"
}


#####
# Main Method
#####
main(){
  echo_verion_file
}


# Pass control to our main Method
main "$@"
