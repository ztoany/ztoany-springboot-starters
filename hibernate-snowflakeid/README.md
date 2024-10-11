# Snowflake Id for Hibernate

## Usage

```java
@Entity
public class Customer {
    @Id
    @SnowflakeId
    private Long id;
    
    // other codes
}
```