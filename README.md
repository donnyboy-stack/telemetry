# Sunseeker Solar Car Project - Telemetry

This repository contains the source for the Sunseeker Solar Car Project's main telemetry application for racing

## Requirements

- Java
- Apache Ant
- rxtx

## Installing and Configuring

Once you have cloned the repository onto your machine, you will want to do the following to get the telemetry up and running.

First, install all of the dependencies:

```sh
$ sudo apt install java-default
$ sudo apt install ant
```

For rxtx
1 Download 	rxtx-2.2pre2-bins.zip from http://rxtx.qbang.org/wiki/index.php/Download
2 unzip rxtx folder
3 Put correct librxtxSerial.so in /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/amd64
4 Put RXTXcomm.jar in /usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext

Second, run telemetry:

```sh
$ ant compile jar
$ ant run
```
