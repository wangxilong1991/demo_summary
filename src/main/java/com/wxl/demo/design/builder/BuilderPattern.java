package com.wxl.demo.design.builder;

/**
 * 构建者
 */
public class BuilderPattern {

    public static void main(String[] args) {
        Person person = Person.builder()
                .name("xxx")
                .age(12)
                .location(new Location("北京", "北京"))
                .build();

    }

}


class Person{
    private String name;
    private int age;
    private Location location;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private Person p = new Person();

        public Builder name(String name){
            p.name = name;
            return this;
        }

        public Builder age(int age){
            p.age = age;
            return this;
        }

        public Builder location(Location loc){
            p.location = loc;
            return this;
        }

        public Person build(){
            return p;
        }
    }

}

class Location{
    private String city;
    private String province;

    public Location(String city, String province) {
        this.city = city;
        this.province = province;
    }
}