/** 
 * Copyright (c) 2009-2011, The HATS Consortium. All rights reserved. 
 * This file is licensed under the terms of the Modified BSD License.
 */

package eu.hats_project.build.maven.plugin;

import java.io.File;


/**
 * A Maven 2 plugin for the ABS To Maude compiler
 * 
 * @goal genmaude
 * @requiresDependencyResolution
 * @phase compile
 */
public class MaudeMojo extends AbstractABSMojo {

    /**
     * The ABS Maude Backend output file.
     * 
     * @parameter expression="${abs.maudeBackend.output}"
     *            default-value="${project.build.directory}/abs/gen/maude/output.maude"
     * @required
     */
    private File absMaudeBackendOutputFile;

    /**
     * @parameter expression="${abs.maudeBackend.mainBlock}"
     */
    private String mainBlock;
    
    /**
     * @parameter expression="${abs.maudeBackend.timed}" 
     *            default-value=false
     */
    private boolean timed;

    @Override
    protected void doExecute() throws Exception {
        MaudeGenerator generator = new MaudeGenerator();
        generator.generateMaude(
                absfrontEnd, 
                absSrcFolder, 
                getABSArguments(), 
                absMaudeBackendOutputFile,
                checkProductSelection,
                verbose,
                stdlib,
                loctype,
                productName,
                mainBlock,
                timed,
                getLog());
    }
    
}
