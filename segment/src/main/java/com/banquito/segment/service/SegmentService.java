package com.banquito.segment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.banquito.segment.model.Segment;
import com.banquito.segment.repository.SegmentRepository;

@Service
public class SegmentService {
    
    private final SegmentRepository segmentRepository;

    public SegmentService(SegmentRepository segmentRepository) {
        this.segmentRepository = segmentRepository;
    }

    public Segment findByName(String name) {
        Segment optSegment = this.segmentRepository.findByName(name);
        if (optSegment == null) {
            throw new RuntimeException("Segment not found");
        }
        return this.segmentRepository.findByName(name);
    }

    //buscar todos los segmentos
    public Iterable<Segment> findAll() {
        return this.segmentRepository.findAll();
    }

    //crear segmento
    @Transactional
    public void createSegment(Segment segment){
        Boolean segmentExists = this.segmentRepository.existsByName(segment.getName());
        if(segmentExists){
            throw new RuntimeException("Segment already exists");
        }
        segment.setName(segment.getName());
        segment.setStatus(segment.getStatus());
        this.segmentRepository.save(segment);
    }

    //crear segmento por idSegmento
    public void createSegmentByIdSegment(Segment segment){
        Boolean segmentExists = this.segmentRepository.existsByIdSegment(segment.getIdSegment());
        if(segmentExists){
            throw new RuntimeException("Segment already exists");
        }
        segment.setIdSegment(segment.getIdSegment());
        segment.setName(segment.getName());
        segment.setStatus(segment.getStatus());
        this.segmentRepository.save(segment);
    }

    //actualizar segmento por name
    @Transactional
    public void updateSegment(String name, Segment segment) {
        Boolean segmentExists = this.segmentRepository.existsByName(name);
        if (!segmentExists) {
            throw new RuntimeException("The segment does not exist");
        }
        else {
            Segment segmentToUpdate = this.segmentRepository.findByName(name);
            segmentToUpdate.setStatus(segment.getStatus());
            this.segmentRepository.save(segmentToUpdate);
            throw new RuntimeException("The segment has been created");
        }
    }

    //actualizar segmento por idSegmento
    @Transactional
    public void updateSegmentByIdSegment(String idSegment, Segment segment) {
        Boolean segmentExists = this.segmentRepository.existsByIdSegment(idSegment);
        if (!segmentExists) {
            throw new RuntimeException("The segment does not exist");
        }
        Segment segmentToUpdate = this.segmentRepository.findByIdSegment(idSegment);
        segmentToUpdate.setStatus(segment.getStatus());
        this.segmentRepository.save(segmentToUpdate);
    }

}
