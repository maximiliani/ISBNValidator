/*
 * Copyright 2021 Karlsruhe Institute of Technology.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.kit.datamanager.datacite.validate.ISBN_validator;

import edu.kit.datamanager.datacite.validate.exceptions.ValidationError;
import edu.kit.datamanager.datacite.validate.exceptions.ValidationWarning;
import org.datacite.schema.kernel_4.RelatedIdentifierType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ISBNValidatorTest {
    ISBNValidator validator = new ISBNValidator();

    @Test
    void valid13() {
        try {
            assertTrue(validator.isValid("9783104909271", RelatedIdentifierType.ISBN));
        } catch (Exception e) {
            fail(e);
        }
    }


    @Test
    void valid10() {
        try {
            assertTrue(validator.isValid("1861972717", RelatedIdentifierType.ISBN));
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void invalidISBN13() {
        try {
            assertFalse(validator.isValid("1234567890123"));
        } catch (ValidationWarning e) {
            fail(e);
        } catch (ValidationError ignored) {
        }
    }

    @Test
    void invalidISBN10() {
        try {
            assertFalse(validator.isValid("1234567890"));
        } catch (ValidationWarning e) {
            fail(e);
        } catch (ValidationError ignored) {
        }
    }

    @Test
    void invalidLength() {
        try {
            assertFalse(validator.isValid("12345678901"));
        } catch (ValidationWarning e) {
            fail(e);
        } catch (ValidationError ignored) {
        }
    }

    @Test
    void invalidType() {
        try {
            assertFalse(validator.isValid("1234567890",RelatedIdentifierType.URL));
        } catch (ValidationWarning ignored) {
        } catch (ValidationError e) {
            fail(e);
        }
    }
}