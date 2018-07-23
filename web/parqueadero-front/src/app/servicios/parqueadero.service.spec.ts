import { TestBed, inject } from '@angular/core/testing';

import { ParqueaderoService } from './parqueadero.service';

describe('ParqueaderoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ParqueaderoService]
    });
  });

  it('should be created', inject([ParqueaderoService], (service: ParqueaderoService) => {
    expect(service).toBeTruthy();
  }));
});
