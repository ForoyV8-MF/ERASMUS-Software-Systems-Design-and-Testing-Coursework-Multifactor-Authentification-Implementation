all : compile test coverage mutation documentation run_Terminal_MFA run_GUI_MFA diagramm report

SRC_MFA_TERMINAL_DIR = src/main/java/MFA_Terminal
SRC_MFA_GUI_DIR = src/main/java/MFA_GUI

SRC_MFA_TERMINAL_TEST_DIR = src/test/java/MFA_Terminal_TEST

CLASSES = target/classes

JUNIT_JAR = target/generated_ressources/jacoco/jacoco_library/lib/junit-4.13.2.jar
HAMCREST_JAR = target/generated_ressources/jacoco/jacoco_library/lib/hamcrest-core-1.3.jar

JACOCO_REP = target/generated_ressources/jacoco/
JACOCO_AGENT_JAR = target/generated_ressources/jacoco/jacoco_library/lib/lib/jacocoagent.jar
JACOCOCLI_JAR = target/generated_ressources/jacoco/jacoco_library/lib/lib/jacococli.jar

PIT_LIBRARY = target/generated_ressources/PIT/PIT_library
PIT_REPORT_DIR = target/generated_ressources/PIT/PIT_report

DOCUMENTATION_OUTPUT = target/generated_ressources/documentation

UML_SRC = src/uml
UML_DIR = src/lateX/Captures

SRC_REPORT_DIR = src/lateX
REPORT_DIR = target/generated_ressources/report

compile:
	javac -cp .:$(JUNIT_JAR) \
	-d $(CLASSES) $(SRC_MFA_TERMINAL_DIR)/*.java $(SRC_MFA_GUI_DIR)/*.java \
	$(SRC_MFA_TERMINAL_TEST_DIR)/*.java

test:
	java -cp $(JUNIT_JAR):$(HAMCREST_JAR):$(CLASSES) \
	org.junit.runner.JUnitCore MFA_Terminal_TEST.ClientsTest \
	MFA_Terminal_TEST.MailCodeTest MFA_Terminal_TEST.SMSCodeTest

coverage:
	java -javaagent:$(JACOCO_AGENT_JAR)=destfile=$(JACOCO_REP)/jacoco.exec \
	-cp $(JUNIT_JAR):$(HAMCREST_JAR):$(SRC_MFA_TERMINAL_DIR):$(SRC_MFA_TERMINAL_TEST_DIR):$(CLASSES) org.junit.runner.JUnitCore \
	MFA_Terminal_TEST.MailCodeTest MFA_Terminal_TEST.SMSCodeTest MFA_Terminal_TEST.ClientsTest

	java -jar $(JACOCOCLI_JAR) report $(JACOCO_REP)/jacoco.exec \
	--classfiles $(CLASSES)/MFA_Terminal \
	--sourcefiles $(SRC_MFA_TERMINAL_DIR) \
	--html $(JACOCO_REP)/jacoco-report

mutation:
	java -cp "$(PIT_LIBRARY)/*:$(CLASSES)/MFA_Terminal:$(CLASSES)/MFA_Terminal_TEST:$(JUNIT_JAR):$(HAMCREST_JAR)" \
	org.pitest.mutationtest.commandline.MutationCoverageReport \
	--reportDir=$(PIT_REPORT_DIR) \
	--targetClasses=MFA_Terminal.* \
	--targetTests=MFA_Terminal_TEST.* \
	--sourceDirs=$(SRC_MFA_TERMINAL_DIR) \
	--classPath $(PIT_LIBRARY)/*:$(CLASSES)/MFA_Terminal:$(CLASSES)/MFA_Terminal_TEST:$(JUNIT_JAR):$(HAMCREST_JAR) \
	--mutators=ALL \

documentation:
	javadoc -d $(DOCUMENTATION_OUTPUT) $(SRC_MFA_TERMINAL_DIR)/*.java $(SRC_MFA_GUI_DIR)/*.java

run_Terminal_MFA:
	clear
	java -cp $(CLASSES) MFA_Terminal.Main

run_GUI_MFA:
	clear
	java -cp $(CLASSES) MFA_GUI.Main

diagram:
	plantuml -d $(UML_DIR) $(UML_SRC)/MFA_GUI.uml

report:
	pdflatex -output-directory=$(REPORT_DIR) $(SRC_REPORT_DIR)/Verin_Clement_Report.tex
	pdflatex -output-directory=$(REPORT_DIR) $(SRC_REPORT_DIR)/Verin_Clement_Report.tex
