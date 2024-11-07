@echo off
@REM -----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one or more
@REM contributor license agreements.  See the NOTICE file distributed with
@REM this work for additional information regarding copyright ownership.
@REM The ASF licenses this file to You under the Apache License, Version 2.0
@REM (the "License"); you may not use this file except in compliance with
@REM the License.  You may obtain a copy of the License at
@REM
@REM     http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing, software
@REM distributed under the License is distributed on an "AS IS" BASIS,
@REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM See the License for the specific language governing permissions and
@REM limitations under the License.
@REM -----------------------------------------------------------------------------

@REM -----------------------------------------------------------------------------
@REM Maven Start Up Batch script
@REM
@REM Required ENV vars:
@REM ------------------
@REM   JAVA_HOME - location of a JDK home dir
@REM
@REM Optional ENV vars
@REM -----------------
@REM   M2_HOME - location of a maven2 dir
@REM   MAVEN_BATCH_ECHO - set to 'on' to enable the echoing of the batch commands
@REM   MAVEN_BATCH_PAUSE - set to 'on' to wait for a key stroke before ending
@REM   MAVEN_OPTS - parameters passed to the Java VM when running Maven
@REM     e.g. to debug Maven itself, use
@REM       set MAVEN_OPTS=-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8000
@REM   MAVEN_SKIP_RC - flag to disable loading of mavenrc files
@REM -----------------------------------------------------------------------------

@REM Begin all REM lines with '@' in case MAVEN_BATCH_ECHO is 'on'
@echo off
@if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%

@REM set %HOME% to equivalent of $HOME
if "%HOME%" == "" (set "HOME=%HOMEDRIVE%%HOMEPATH%")

@REM Execute a user defined script before this one
if not "%MAVEN_SKIP_RC%" == "" goto skipRcPre
if exist "%HOME%\mavenrc_pre.bat" call "%HOME%\mavenrc_pre.bat"
if exist "%M2_HOME%\bin\mavenrc_pre.bat" call "%M2_HOME%\bin\mavenrc_pre.bat"
:skipRcPre

@setlocal

set ERROR_CODE=0

@REM To isolate internal variables from possible post scripts, we use another setlocal
@setlocal

@REM OS specific support.  %var% names are deliberately both UPPER and lower case
@REM to avoid clashes with the names of environment variables.
if not "%OS%" == "Windows_NT" goto Win9xME
setlocal enabledelayedexpansion
if "%MAVEN_PROJECTBASEDIR%" == "" set MAVEN_PROJECTBASEDIR=%CD%
@REM %~dp0 is expanded pathname of the current script under NT
set MAVEN_BATCH_FILE=%~f0
goto init

:Win9xME
@REM %~dp0 is not supported under Win9x/ME
set MAVEN_BATCH_FILE=%0
if "%MAVEN_PROJECTBASEDIR%" == "" set MAVEN_PROJECTBASEDIR=%CD%
goto init

:init
@REM Find the project base dir, i.e. the directory that contains the folder ".mvn".
set MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR:"=%

if exist "%MAVEN_PROJECTBASEDIR%\..\.mvn" (
    set MAVEN_PROJECTBASEDIR=%MAVEN_PROJECTBASEDIR%\..
    goto init
)

@REM For Cygwin, ensure paths are in UNIX format before anything is touched
if exist "%HOME%\bin\cygpath" (
    set "MAVEN_PROJECTBASEDIR=%HOME%\bin\cygpath -u %MAVEN_PROJECTBASEDIR%"
    set "M2_HOME=%HOME%\bin\cygpath -u %M2_HOME%"
    set "JAVA_HOME=%HOME%\bin\cygpath -u %JAVA_HOME%"
)

@REM Determine the Java command to use to start the JVM.
if not "%JAVA_HOME%" == "" goto findJavaFromJavaHome

set JAVA_EXE=java
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto initJava

echo Error: JAVA_HOME is not defined correctly.
echo  We cannot execute %JAVA_EXE%.
goto end

:findJavaFromJavaHome
set JAVA_EXE=%JAVA_HOME%\bin\java.exe

if exist "%JAVA_EXE%" goto initJava
echo Error: JAVA_HOME is not defined correctly.
echo  We cannot execute %JAVA_EXE%.
goto end

:initJava
@REM Fully qualified path to the java executable
@set JAVA_CMD=%JAVA_EXE%

@REM Setup the command line
@set MAVEN_CMD_LINE_ARGS=%*
@set MAVEN_OPTS=%MAVEN_OPTS:"=%

@REM Enable echoing of batch commands if MAVEN_BATCH_ECHO is 'on'
@if "%MAVEN_BATCH_ECHO%" == "on"  echo %MAVEN_BATCH_ECHO%

@REM Execute a user defined script after this one
if not "%MAVEN_SKIP_RC%" == "" goto skipRcPost
if exist "%HOME%\mavenrc_post.bat" call "%HOME%\mavenrc_post.bat"
if exist "%M2_HOME%\bin\mavenrc_post.bat" call "%M2_HOME%\bin\mavenrc_post.bat"
:skipRcPost

@REM Launch Maven
"%JAVA_CMD%" %MAVEN_OPTS% -classpath "%M2_HOME%\boot\plexus-classworlds-2.x.jar" "-Dclassworlds.conf=%M2_HOME%\bin\m2.conf" "-Dmaven.home=%M2_HOME%" "-Dmaven.multiModuleProjectDirectory=%MAVEN_PROJECTBASEDIR%" org.codehaus.plexus.classworlds.launcher.Launcher %MAVEN_CMD_LINE_ARGS%

@endlocal & set ERROR_CODE=%ERRORLEVEL%

@if not "%MAVEN_BATCH_PAUSE%" == "" pause

@endlocal & set ERROR_CODE=%ERRORLEVEL%

@if "%MAVEN_BATCH_PAUSE%" == "on" pause

@endlocal & set ERROR_CODE=%ERRORLEVEL%

@if "%MAVEN_BATCH_PAUSE%" == "on" pause

goto end

:end
@exit /b %ERROR_CODE%