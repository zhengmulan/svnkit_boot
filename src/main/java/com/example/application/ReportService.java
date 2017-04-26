/**
 * Copyright 2015-2016 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.application;

import com.example.domain.TemplateService;
import com.example.domain.entity.ReportModel;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmatesoft.svn.core.SVNException;

import java.io.IOException;
import java.text.ParseException;

/**
 * Purpose.
 *
 * A description of why this class exists.  
 *   For what reason was it written?  
 *   Which jobs does it perform?
 * {@code DataAccessException} using 
 * @author how
 * @date 17/4/26
 */
@Service
public class ReportService {

    @Autowired
    private SvnService svnService;

    public void generateReport(ReportModel reportModel) throws IOException, TemplateException, SVNException, ParseException {
        TemplateService<ReportModel> ts = new TemplateService<ReportModel>("report.ftl",reportModel);
        String result = ts.process();
        String datePath = reportModel.getEndDay().replaceAll("-","");
        svnService.commit(result,datePath,reportModel.getCommitter());
    }
}
