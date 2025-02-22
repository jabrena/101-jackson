package info.jab.java.jackson.jsonmerge.map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PersonWithContactsTest {

    @Test
    void should_merge_contacts_map_when_updating_person() throws Exception {
        // Given
        PersonWithContacts person = new PersonWithContacts();
        person.setName("John Doe");
        person.getContacts().put("email", "john@example.com");
        person.getContacts().put("phone", "123-456-7890");

        String jsonToMerge = """
            {
                "name": "John Doe",
                "contacts": {
                    "phone": "999-999-9999",
                    "twitter": "@johndoe"
                }
            }
            """;

        // When
        ObjectMapper mapper = new ObjectMapper();
        PersonWithContacts updatedPerson = mapper.readerForUpdating(person)
                .readValue(jsonToMerge);

        // Then
        assertThat(updatedPerson.getName()).isEqualTo("John Doe");
        assertThat(updatedPerson.getContacts())
            .hasSize(3)
            .containsEntry("email", "john@example.com")
            .containsEntry("phone", "999-999-9999")
            .containsEntry("twitter", "@johndoe");
    }
} 