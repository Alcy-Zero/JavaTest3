FROM registry.saas.hand-china.com/hap-cloud/base:latest

COPY ./Exam1/target/Exam1.jar /Exam1.jar

CMD ["java", "-jar", "Exam1.jar"]

