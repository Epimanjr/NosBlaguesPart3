#! /bin/sh
echo $CLASSPATH

cd build/classes

echo lancement du rmi dans `pwd`

rmiregistry

