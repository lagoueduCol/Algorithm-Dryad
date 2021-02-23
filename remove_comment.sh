#!/bin/bash

for n in `find . -name "*.py"`; do  sed -i "" "/@lc/d" $n; done
for n in `find . -name "*.java"`; do  sed -i "" "/@lc/d" $n; done
for n in `find . -name "*.cpp"`; do  sed -i "" "/@lc/d" $n; done

