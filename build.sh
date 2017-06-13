#!/bin/bash
#set -x
TOMCAT_HOME=/opt/bp/tomcat-8-5-6
RELEASE=mustang
BUILD_HOME=/opt/bp/build
CODE_HOME=${BUILD_HOME}/${RELEASE}
export JAVA_HOME=/usr/java/jdk1.8.0_92

cd $BUILD_HOME
rm -f buildlog.txt
exec >> buildlog.txt
exec 2>&1
echo Build started on `date`
CWD=`pwd`

#source ${BUILD_HOME}/build_init

echo
echo "Starting build..."

echo
echo "Cloning fresh Code Base..."
rm -rf ${RELEASE}
sleep 2
git clone https://github.com/kirmich/mustang.git
sleep 2
echo
echo "Code Base Cloned..."

echo
echo "Building Backend deployables..."
cd ${CODE_HOME}
/opt/maven/bin/mvn package
sleep 2
echo
echo "Built Backend deployables..."

echo
echo "Deploying Backend..."
echo
echo "Stopping tomcat..."
cd ${TOMCAT_HOME}/bin
./shutdown.sh
sleep 2
echo
echo "Cleaning up tomcat..."
cd ${TOMCAT_HOME}/temp
rm -rf *
cd ${TOMCAT_HOME}/logs
rm -rf *
cd ${TOMCAT_HOME}/work
rm -rf *
cd ${TOMCAT_HOME}/webapps
rm -rf *
sleep 2
echo
echo "Copying war files..."
#cd ${CODE_HOME}/userapi/target/
cp ${CODE_HOME}/userapi/target/userapi-*.war ${TOMCAT_HOME}/webapps/userapi.war
sleep 2
#cd ${CODE_HOME}/walletapi/target/
cp ${CODE_HOME}/walletapi/target/walletapi-*.war ${TOMCAT_HOME}/webapps/walletapi.war
sleep 2
#cd ${CODE_HOME}/waltxnapi/target/
cp ${CODE_HOME}/waltxnapi/target/waltxnapi-*.war ${TOMCAT_HOME}/webapps/waltxnapi.war
sleep 2
#cd ${CODE_HOME}/demoinit/target/
cp ${CODE_HOME}/demoinit/target/demoinit-*.war ${TOMCAT_HOME}/webapps/demoinit.war
sleep 2
echo
echo "Copied all the wars..."

echo
echo "Starting tomcat..."
cd ${TOMCAT_HOME}/bin
./startup.sh
sleep 2
echo
echo "Building UI deployables..."
cd ${CODE_HOME}/webapps/

npm install
sleep 1
bower --allow-root install
sleep 1
grunt build
sleep 1
grunt sim
sleep 1
cd build/
echo
echo "Built UI deployables..."

echo
echo "Deploying UI..."
rm -rf /var/www/html/bp/*
sleep 1
cp -R * /var/www/html/bp/
sleep 2
cd /var/www/html/bp/
ln -s /opt/bp/build/mustang/kdbddtest/target/site/serenity test
sleep 1
service httpd restart
sleep 1
echo
echo "Deployed UI..."

cd ${BUILD_HOME}

echo "Deployment Complete..."
echo

#echo "New Build Available for Mustang." | mutt -s "Mustang New Build Deployed" "p.roy@oberthur.com"
cat buildlog.txt | mutt -s "Mustang New Build Deployed" "p.roy@oberthur.com"
sleep 3m
rm -f buildlog.txt
exec >> buildlog.txt
exec 2>&1

cd ${CODE_HOME}

/opt/maven/bin/mvn clean install
sleep 5
cd ${BUILD_HOME}
echo Test ended on `date`
#echo "Test Results" | mutt -s "Test Results of Mustang" "p.roy@oberthur.com"
sed -n '/ T E S T S/,$p' buildlog.txt | mutt -s "Test Results of Mustang" "p.roy@oberthur.com"
