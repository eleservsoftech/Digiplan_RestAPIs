package com.digiplan.services;

import java.util.List;

import com.digiplan.entities.ForgeViewer;

public interface ForgeViewerService {

    ForgeViewer getForgeViewer(Integer id);

    List<ForgeViewer> getAllForgeViewers();

    ForgeViewer addForgeViewer(ForgeViewer forgeViewerData);

    ForgeViewer updateForgeViewer(Integer id, ForgeViewer forgeViewerData);

    String deleteForgeViewer(Integer id);
}
