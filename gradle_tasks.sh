#!/bin/bash

# Java 21 を明示的に使用
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
export PATH=$JAVA_HOME/bin:$PATH

# コマンド引数によって実行内容を切り替える
case "$1" in
  build)
    ./gradlew build
    ;;
  run)
    ./gradlew bootRun
    ;;
  clean)
    ./gradlew clean
    ;;
  check)
    ./gradlew check
    ;;
  lint)
    ./gradlew ktlintCheck
    ;;
  format)
    ./gradlew ktlintFormat
    ;;
  *)
    echo "Usage: $0 {build|run|clean|check|lint|format}"
    exit 1
    ;;
esac
