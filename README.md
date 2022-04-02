
# Skeson
JSON Library for Java with a focus on providing a clean DSL


```java
obj(
    name -> lit("Vaishnav"),
    age -> lit(21),
    address -> obj(
        street -> lit("Some where"),
        city -> lit("In some City"),
        state -> lit("In some State")
    )
)
```
```
{"name": "Vaishnav","age": 21,"address": {"street": "Some where","city": "In some City","state": "In some State"}}
```

## Usage Example

### Type 1 (Preffered)
<details>
  <summary>JsonAdapter</summary>
  
  ```java
class Human {
    private final String name;
    private final int age;
    private final int height;

    Human(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Human human = (Human) o;
        return getAge() == human.getAge() && getHeight() == human.getHeight() && Objects.equals(getName(), human.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getHeight());
    }
}

class HumanJsonAdapter implements JsonAdapter < Human > {

    @Override
    public JsonNode toJson(final Human object) {
        final String objectName = object.getName();
        final int objectAge = object.getAge();
        final int objectHeight = object.getHeight();
        return obj(
            name - > lit(objectName),
            age - > lit(objectAge),
            height - > lit(objectHeight)
        );
    }

    @Override
    public Human fromJson(final Consumer < JsonNode > readNode) {
        final LiteralReadingJsonNode < String > nameMatcher = matchString();
        final LiteralReadingJsonNode < Number > ageMatcher = matchNumber();
        final LiteralReadingJsonNode < Number > heightMatcher = matchNumber();
        readNode.accept(obj(
            name - > nameMatcher,
            age - > ageMatcher,
            height - > heightMatcher
        ));
        return new Human(nameMatcher.getValue(), ageMatcher.getValue().intValue(), heightMatcher.getValue().intValue());
    }
}
```
</details>


### Type 2
<details>
  <summary>Plain Json Read</summary>
  
 ```java
final JsonNode node = obj(
    name -> lit("Vaishnav"),
    age -> lit(21),
    address -> obj(
        street -> lit("Some where"),
        city -> lit("In some City"),
        state -> lit("In some State")
    )
);
System.out.println(node);
```

</details>

  <details>
  <summary>Plain Json Write</summary>
  
 ```java
final JsonNode node = obj(
    name -> lit("Vaishnav"),
    age -> lit(21),
    address -> obj(
        street -> lit("Some where"),
        city -> lit("In some City"),
        state -> lit("In some State")
    )
);
System.out.println(node);
```

</details>
