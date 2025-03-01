package info.jab.java.jackson.jsonmerge.issue185;

import com.fasterxml.jackson.annotation.JsonMerge;
import com.fasterxml.jackson.annotation.OptBoolean;

import java.util.List;
import java.util.Map;

import java.util.Objects;

//TODO replace with record in the future
public class ParentJava {

  @JsonMerge(OptBoolean.FALSE)
  List<String> list;

  @JsonMerge
  Map<String, String> map;

  @JsonMerge
  Map<String, Map<String, String>> deepMap;

  @JsonMerge
  Child child;

  private ParentJava() {
  }

  private ParentJava(
    List<String> list, Map<String, String> map, 
    Map<String, Map<String, String>> deepMap, Child child) {
    this.list = list;
    this.map = map;
    this.deepMap = deepMap;
    this.child = child;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParentJava that = (ParentJava) o;
    return Objects.equals(list, that.list) &&
            Objects.equals(map, that.map) &&
            Objects.equals(deepMap, that.deepMap) &&
            Objects.equals(child, that.child);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list, map, deepMap, child);
  }

  @Override
  public String toString() {
    return "ParentJava{" +
           "list=" + list +
           ", map=" + map +
           ", deepMap=" + deepMap +
           ", child=" + child +
           '}';
  }
}
