package dk.statsbiblioteket.medieplatform.contentresolver.lib;

/*
 * #%L
 * content-resolver-lib
 * %%
 * Copyright (C) 2012 The State and University Library, Denmark
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.junit.Test;

import dk.statsbiblioteket.medieplatform.contentresolver.model.Content;

import java.net.URI;

import static org.junit.Assert.*;

/** Test configurable content resolver. */
public class ConfigurableContentResolverTest {
    @Test
    public void testConfiguredContentResolver() throws Exception {
        ContentResolver contentResolver = new ConfigurableContentResolver(getClass().getClassLoader().getResource("testconfiguration.xml").getPath());
        // Lookup a resource in the previews directory
        Content content = contentResolver.getContent("00001ecd-f3d8-4aac-a486-093e45b079a0");

        // Check result: Exactly one uri of type preview.
        assertEquals(2, content.getResources().size());
        assertEquals("preview", content.getResources().get(0).getType());
        assertEquals(1, content.getResources().get(0).getUris().size());
        assertEquals(
                new URI("rtsp://example.com/bart/preview/0/0/0/0/00001ecd-f3d8-4aac-a486-093e45b079a0.preview.flv"),
                content.getResources().get(0).getUris().get(0));

        // Check result: 5 uris in one resource of type thumbnail.
        assertEquals("thumbnails", content.getResources().get(1).getType());
        assertEquals(5, content.getResources().get(1).getUris().size());
        assertEquals(new URI("http://example.com/bart/thumbnail/00001ecd-f3d8-4aac-a486-093e45b079a0.snapshot.0.jpeg"),
                     content.getResources().get(1).getUris().get(0));
        assertEquals(new URI("http://example.com/bart/thumbnail/00001ecd-f3d8-4aac-a486-093e45b079a0.snapshot.1.jpeg"),
                     content.getResources().get(1).getUris().get(1));
        assertEquals(new URI("http://example.com/bart/thumbnail/00001ecd-f3d8-4aac-a486-093e45b079a0.snapshot.2.jpeg"),
                     content.getResources().get(1).getUris().get(2));
        assertEquals(new URI("http://example.com/bart/thumbnail/00001ecd-f3d8-4aac-a486-093e45b079a0.snapshot.3.jpeg"),
                     content.getResources().get(1).getUris().get(3));
        assertEquals(
                new URI("http://example.com/bart/thumbnail/00001ecd-f3d8-4aac-a486-093e45b079a0.snapshot.preview.0.jpeg"),
                content.getResources().get(1).getUris().get(4));

    }
}