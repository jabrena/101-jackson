package info.jab.java.jackson.jsonmerge;

/**
 * public @interface JsonMerge
 * Annotation to specify whether annotated property value should use "merging" approach: merging meaning that the current value is first accessed (with a getter or field) and then modified with incoming data. If merging is not used assignment happens without considering current state.
 * Merging is only option if there is a way to introspect current state: if there is accessor (getter, field) to use. Merging can not be enabled if no accessor exists or if assignment occurs using a Creator setter (constructor or factory method), since there is no instance with state to introspect. Merging also only has actual effect for structured types where there is an obvious way to update a state. For example, POJOs have default values for properties, and Collections and Maps may have existing elements; whereas scalar types do not such state: an int has a value, but no obvious and non-ambiguous way to merge state.
 * 
 * Merging is applied by using a deserialization method that accepts existing state as an argument: it is then up to JsonDeserializer implementation to use that base state in a way that makes sense without further configuration. For structured types this is usually obvious; and for scalar types not -- if no obvious method exists, merging is not allowed; deserializer may choose to either quietly ignore it, or throw an exception. Specifically, for structured types:
 * 
 * - For POJOs merging is done recursively, property by property.
 * - For Maps merging is done recursively, entry by entry .
 * - For Collection and Arrays, merging is done by appending incoming data into contents of existing Collection/array (and in case of Arrays, creating a new Array instance). NOTE! This is different from JSON Merge Patch.
 * - For Scalar values, incoming value replaces existing value, that is, no merging occurs.
 * 
 * Note that use of merging usually adds some processing overhead since it adds an extra step of accessing the current state before assignment.
 * 
 * Note also that "root values" (values directly deserialized and not reached via POJO properties) can not use this annotation; instead, ObjectMapper and Object have "updating reader" operations.
 * 
 * Default value is OptBoolean.TRUE, that is, merging is enabled.
 */