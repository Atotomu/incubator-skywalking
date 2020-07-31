/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package test.org.apache.skywalking.apm.testcase.retransform_class;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
@RequestMapping("/case")
public class CaseController {

    private static final String SUCCESS = "Success";

    @RequestMapping("/retransform-class")
    @ResponseBody
    public ResponseEntity testcase() throws HttpStatusCodeException {
        if (RetransformUtil.RETRANSFORMING_TAG.equals(RetransformUtil.RETRANSFORM_VALUE)) {
            System.out.println("retransform check success.");
            return ResponseEntity.ok("retransform success");
        } else {
            System.out.println("retransform check failure.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("retransform failure");
        }
    }

    @RequestMapping("/healthCheck")
    @ResponseBody
    public String healthCheck() {
        // your codes
        return SUCCESS;
    }



}
