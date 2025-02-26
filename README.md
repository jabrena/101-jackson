# 101 Jackson

A way to learn details about Jackson.

## What is Jackson?

Jackson has been known as "the Java JSON library" 

## Build

```bash
./mvnw clean verify
./mvnw clean verify -Dtest=DepartmentVAVRTest  

./mvnw versions:display-dependency-updates
./mvnw versions:display-plugin-updates
```

## JSON Merge

public @interface JsonMerge
Annotation to specify whether annotated property value should use "merging" approach: merging meaning that the current value is first accessed (with a getter or field) and then modified with incoming data. If merging is not used assignment happens without considering current state.
Merging is only option if there is a way to introspect current state: if there is accessor (getter, field) to use. Merging can not be enabled if no accessor exists or if assignment occurs using a Creator setter (constructor or factory method), since there is no instance with state to introspect. Merging also only has actual effect for structured types where there is an obvious way to update a state. For example, POJOs have default values for properties, and Collections and Maps may have existing elements; whereas scalar types do not such state: an int has a value, but no obvious and non-ambiguous way to merge state.

Merging is applied by using a deserialization method that accepts existing state as an argument: it is then up to JsonDeserializer implementation to use that base state in a way that makes sense without further configuration. For structured types this is usually obvious; and for scalar types not -- if no obvious method exists, merging is not allowed; deserializer may choose to either quietly ignore it, or throw an exception. Specifically, for structured types:

- For POJOs merging is done recursively, property by property.
- For Maps merging is done recursively, entry by entry .
- For Collection and Arrays, merging is done by appending incoming data into contents of existing Collection/array (and in case of Arrays, creating a new Array instance). NOTE! This is different from JSON Merge Patch.
- For Scalar values, incoming value replaces existing value, that is, no merging occurs.

Note that use of merging usually adds some processing overhead since it adds an extra step of accessing the current state before assignment.

Note also that "root values" (values directly deserialized and not reached via POJO properties) can not use this annotation; instead, ObjectMapper and Object have "updating reader" operations.

Default value is OptBoolean.TRUE, that is, merging is enabled.

---

### Test Results

| Type | POJO | List | Map | Map Deep Merge | Scalar |
|------|------|------|-----|----------------|--------|
| JDK  | [OK](./src/main/java/info/jab/java/jackson/jsonmerge/pojo/Employee.java) | [KO](./src/test/java/info/jab/java/jackson/jsonmerge/list/DepartmentTest.java) | [OK](./src/test/java/info/jab/java/jackson/jsonmerge/map/PersonTest.java) | [OK](./src/test/java/info/jab/java/jackson/jsonmerge/mapdm/NestedMapContainerTest.java) | [OK](src/main/java/info/jab/java/jackson/jsonmerge/scalar/Product.java) |
| Vavr | NA   | [KO](./src/test/java/info/jab/java/jackson/jsonmerge/list/DepartmentVAVRTest.java) | [KO](./src/test/java/info/jab/java/jackson/jsonmerge/map/PersonVAVRTest.java)  | [KO](./src/test/java/info/jab/java/jackson/jsonmerge/mapdm/NestedMapContainerVAVRTest.java)         | NA     |

## References

- https://github.com/FasterXML/jackson
- https://github.com/FasterXML/jackson-docs
- https://github.com/FasterXML/jackson-databind
- https://github.com/FasterXML/jackson-core
- https://github.com/FasterXML/jackson-annotations
- https://github.com/FasterXML/jackson-annotations/blob/master/src/main/java/com/fasterxml/jackson/annotation/JsonMerge.java
- https://www.javadoc.io/doc/com.fasterxml.jackson.core/jackson-annotations/latest/com/fasterxml/jackson/annotation/JsonMerge.html
- https://github.com/vavr-io/vavr-jackson
- https://github.com/vavr-io/vavr-jackson/blob/main/src/main/java/io/vavr/jackson/datatype/VavrModule.java
- https://github.com/vavr-io/vavr-jackson/blob/main/src/main/java/io/vavr/jackson/datatype/deserialize/VavrDeserializers.java
- https://github.com/FasterXML/jackson-databind/blob/2.19/src/main/java/com/fasterxml/jackson/databind/deser/Deserializers.java
- https://github.com/vavr-io/vavr-jackson/blob/main/src/main/java/io/vavr/jackson/datatype/deserialize/MapDeserializer.java
- https://github.com/vavr-io/vavr-jackson/blob/main/src/main/java/io/vavr/jackson/datatype/deserialize/MaplikeDeserializer.java
- https://github.com/FasterXML/jackson-databind/blob/2.19/src/main/java/com/fasterxml/jackson/databind/deser/std/StdDeserializer.java
- https://github.com/FasterXML/jackson-databind/blob/2.19/src/main/java/com/fasterxml/jackson/databind/deser/ContextualDeserializer.java

### Learning

- https://docs.oracle.com/en/java/javase/23/docs/specs/serialization/index.html
- https://www.youtube.com/watch?v=dOgfWXw9VrI&ab_channel=Devoxx
- https://github.com/FasterXML/jackson-docs
- https://github.com/FasterXML/jackson-docs/wiki/_pages
- https://visionarysoftware.solutions/visions/java/serialization/architecture.html
- https://www.baeldung.com/jackson
- https://www.baeldung.com/jackson-annotations
- https://www.baeldung.com/jackson-object-mapper-tutorial
- https://www.baeldung.com/jackson-custom-serialization
- https://www.baeldung.com/jackson-custom-deserialization
- https://www.baeldung.com/jackson-json-merge

## Cursor AI Prompts

```bash
create a empty test class without any test and not give any explanation
add equals and hashcode & toString method to the class and not give any explanation
evolve the tests using Given When Then and not give any explanation
refactor with assertj and not give any explanation
use textblocks to create json strings and not give any explanation
put indented json in the textblocks with json
add a comment in class in the section for properties, constructor, getters, setters, equals, hashcode, toString and not give any explanation
all tests need to have an assert
```
